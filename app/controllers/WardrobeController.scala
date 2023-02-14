package controllers

import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import services.WardrobeServiceRepository

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class WardrobeController @Inject()(cc: ControllerComponents, wardrobeService: WardrobeServiceRepository) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Welcome to Wardrobe Management Application"))
  }

  def uploadData() = Action.async {
   val file = scala.io.Source.fromFile("conf/resources/clothing.csv").getLines().toList
    wardrobeService.saveData(file).map { res =>
      Ok(Json.toJson(res))
    }
  }

  def getRecords() = Action.async {
    wardrobeService.getAll().map { res =>
        Ok(Json.toJson(res))
    }
  }

  def getRecordByName(name: String) = Action.async {
    wardrobeService.get(name).map { res =>
      Ok(Json.toJson(res))
    }
  }
}
