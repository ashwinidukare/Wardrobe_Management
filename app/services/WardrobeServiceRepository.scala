package services

import db.{DB, WardrobeTable}
import models.Wardrobe
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.Json
import slick.jdbc.JdbcProfile

import java.util.UUID
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class WardrobeServiceRepository  @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit val executionContext: ExecutionContext)
  extends DB with WardrobeTable {

  import profile.api._


  def saveData(wardrobes:List[String] ) = {
    val lists = wardrobes.drop(1).map { x =>
      val data = x.split(", ")
        val wardrobe1 = Wardrobe(UUID.randomUUID().toString,data.head, data.last)
        dbConfig.db.run(wardrobe += wardrobe1).map(res=> Json.toJson(wardrobe1))
    }
    Future.sequence(lists)
  }

  def get(name: String): Future[Seq[Wardrobe]] = {
    dbConfig.db.run(wardrobe.filter(_.name===name).result)
  }

  def getAll(): Future[Seq[Wardrobe]] = {
    dbConfig.db.run(wardrobe.result)
  }
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]
}
