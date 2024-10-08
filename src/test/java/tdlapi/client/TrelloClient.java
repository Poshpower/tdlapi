package tdlapi.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static tdlapi.constant.ProjectConstants.API_KEY;
import static tdlapi.constant.ProjectConstants.API_TOKEN;

public class TrelloClient {

    private static RequestSpecification trelloSpec(){
      return RestAssured
              .given().log().all()
              .contentType(ContentType.JSON)
              .queryParam("key",API_KEY)
              .queryParam("token", API_TOKEN);
    }

    public static Response getBoardInfo(String boardId){
        return RestAssured
              .given(trelloSpec())
              .when()
              .get("https://api.trello.com/1/boards/" + boardId)
              .then().log().all()
              .statusCode(200)
              .extract().response();
    }

    public static Response changeBoardName(String name, String boardId){
       return RestAssured
               .given(trelloSpec())
               .queryParam("name", name)
               .when()
               .put("https://api.trello.com/1/boards/" + boardId)
               .then().log().all()
               .statusCode(200)
               .extract().response();
    }

    public static Response createList(String name,String boardId){
        return RestAssured
                .given(trelloSpec())
                .queryParam("name", name)
                .queryParam("idBoard", boardId)
                .when()
                .post("https://api.trello.com/1/lists")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response deleteList(String id){
        return RestAssured
                .given(trelloSpec())
                .queryParam("value", true)
                .when()
                .put("https://api.trello.com/1/lists/"+ id + "/closed")
                .then().log().all()
                .statusCode(200)
                .extract().response();
   }
}
