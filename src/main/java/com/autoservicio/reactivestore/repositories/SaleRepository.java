package com.autoservicio.reactivestore.repositories;

import java.time.LocalDate;
import java.util.Date;

import org.bson.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.autoservicio.reactivestore.dto.Purchase;

import reactor.core.publisher.Flux;

public interface SaleRepository extends ReactiveMongoRepository<Purchase, String> {
	@Aggregation(pipeline={"{$match: {\r\n"
			+ "  purchaseDate:{$gte:?0,\r\n"
			+ "    $lte:?1},\r\n"
			+ "    \"items.barcode\":?2}}"
			,"{$project: {\r\n"
			+ "  purchaseDate:1,\r\n"
			+ "  \"items\":{\r\n"
			+ "    $filter:{\r\n"
			+ "     \"input\":\"$items\",\r\n"
			+ "     \"as\": \"item\",\r\n"
			+ "     \"cond\":{\r\n"
			+ "       \"$eq\":[\"$$item.barcode\",?2]\r\n"
			+ "     }\r\n"
			+ "    }\r\n"
			+ "  }}}"
			,"{$project: {\r\n"
			+ "  \"purchaseDate\":1,\r\n"
			+ "  \"items.description\":1,\r\n"
			+ "  \"items.quantity\":1\r\n"
			+ "}}"})
	Flux<Purchase>findPurchasedProductInPeriod(Date startDate,Date endDate,String barcode);
	
	@Aggregation(pipeline= {"{$match: {\r\n"
			+ "  purchaseDate:{$gte:?0,\r\n"
			+ "    $lte:?1\r\n"
			+ "  }\r\n"
			+ "}}"
			,"{$project: {\r\n"
			+ "  day:{$isoDayOfWeek:\"$purchaseDate\"},\r\n"
			+ "  totalPurchasePrice:1,\r\n"
			+ "  totalSellingPrice:1\r\n"
			+ "}}"
			,"{$group: {\r\n"
			+ "  _id:{ 'isoDay':$day},\r\n"
			+ "  finalSellingPrice: {\r\n"
			+ "    $sum:$totalSellingPrice"
			+ "  },\r\n"
			+"	finalPurchasePrice:{\r\n"
			+"		$sum:$totalPurchasePrice"
			+"	}\r\n"
			+ "}}"
			,"{$sort:{_id:1}}"})
	Flux<Document>findTotalSalePerDayInPeriod(LocalDate startDate,LocalDate endDate);
}
