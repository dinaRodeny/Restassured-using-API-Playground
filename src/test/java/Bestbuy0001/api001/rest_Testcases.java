package Bestbuy0001.api001;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.*;


import java.util.HashMap;
import java.util.Map;

import        org.codehaus.groovy.transform.EqualsAndHashCodeASTTransformation;
import org.json.simple.JSONObject;
import        org.testng.Assert;
import        org.testng.annotations.Test;
import        io.restassured.RestAssured;
import io.restassured.http.ContentType;
import        io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.module.jsv.JsonSchemaValidator;
//import        io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;;


public class rest_Testcases {
	/***********************************************************************************************************************************************************************
	 *Validate that the response is returned from "get" successfully with the entire data 
	 *parameter in:  void
	 *parameter out: void
	 *Trace:Getproduct0001
	 ************************************************************************************************************************************************************************/
	@Test(enabled = true,priority =1)
  public void get_allDataUsingGherkins()
  {
	  	given().
	  		    header("Content-Type","application/json").
	  		   // param(paramtername, parametervalue).
	  	        get("http://localhost:3030/products").
	  	then ().
	  	        statusCode(200).
	  	        body("data[0].id",equalTo(43900)).
	  	        body("data.name",hasItems("Duracell - AAA Batteries (4-Pack)")).
	            log().all();
  }
	 
	 /***********************************************************************************************************************************************************************
		 *Validate that the response is returned from "get" successfully for specific ID
		 *parameter in:  void
		 *parameter out: void
		 *Trace:Getproduct0002
		 ************************************************************************************************************************************************************************/
	 @Test(enabled = true,priority =2)
	public void getSpecificID()
	{
		given().
		header("Content-Type","application/json").
		param ( "id", 48530 ).
		get("http://localhost:3030/products").
		then ().
		statusCode(200).
		body("data.name",hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)")).
		log().all();
	}

	/***********************************************************************************************************************************************************************
	 *Validate that the response is returned from get API successfully by saving it in the "resp_Body"then assert that 200 response is retured (using XML only)
	 *parameter in:  void
	 *parameter out: void
	 *Trace:Getproduct0002
	 ************************************************************************************************************************************************************************/
	 @Test(enabled = true,priority =3)
	public void get_Json()
	{

		JSONObject request=new JSONObject();
		given().
		    header("Content-Type","application/json") .
		    accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			get("http://localhost:3030/stores").
		then().	
		   // assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema.json")).
		    assertThat().body(matchesJsonSchemaInClasspath("service_Getall.json")).
			statusCode(200)	;	
	}
		
	
	/***********************************************************************************************************************************************************************
	 *Validate that the patch function is working correctly without any errors
	 *parameter in:  void
	 *parameter out: void
	 *Trace:Getproduct000
	 ************************************************************************************************************************************************************************/
	@Test(enabled = true,priority =4)
	public void Patch()
	{
		JSONObject request=new JSONObject();
		String id="48530";
		request.put("price", "709");
		baseURI="http://localhost:3030";
		given().header("Content-Type","application/json").
		param ( "id",id ).
	    accept(ContentType.JSON).
		body(request.toJSONString()).
	when().
		patch("/products/"+id).
	then().	
		statusCode(200).
		log().all();
	
	}
	/***********************************************************************************************************************************************************************
	 *Validate that the del function is deleted successfully
	 *parameter in:  void
	 *parameter out: void
	 *Trace:Getproduct0002
	 **********************************************************************************************************************************************************/
	
	@Test(enabled = true,priority =5)
	public void Del()
	{
		
		
		JSONObject request=new JSONObject();
		String id="43900";
		baseURI="http://localhost:3030";
		given().
		param ( "id",id ).
	when().
		delete("/products/"+id).
	then().	
		statusCode(200).
		log().all();
		
	}

	
	
	/***********************************************************************************************************************************************************************
	 *Validate that the patch function is working correctly without any errors
	 *parameter in:  void
	 *parameter out: void
	 *Trace:Getproduct000
	 ************************************************************************************************************************************************************************/
	@Test(enabled = true,priority =4)
	public void Post()
	{
		JSONObject request=new JSONObject();
		//String RequestBody ={}
			
		request.put("name", "Dina");
		request.put("type", "sandwich");
		request.put("price", "300");
		request.put("shipping", "90");
		request.put("upc", "90hh");
		request.put("upc", "01001h");
		baseURI="http://localhost:3030";
		given().header("Content-Type","application/json").
	    accept(ContentType.JSON).
		body(request.toJSONString()).
	when().
		post("/products").
	then().	
		statusCode(200).
		log().all();
	
	}
	
}
