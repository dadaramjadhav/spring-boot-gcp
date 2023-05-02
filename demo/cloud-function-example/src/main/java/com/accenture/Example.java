package com.accenture;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.BufferedWriter;
import java.util.Optional;

public class Example implements HttpFunction {
  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {
	double amountInInr = 0.0;
	double amountInUsd = 0.0;
	double usdRate = 81.35;
	BufferedWriter writer = response.getWriter();
    Optional<String> requestedAmount = request.getFirstQueryParameter("amount");
    if(requestedAmount.isPresent()) {
    	amountInUsd = Double.parseDouble(requestedAmount.get());
    	amountInInr = amountInUsd * usdRate;
    }
    writer.write("Amount is rupees is : " + amountInInr);
  }
}