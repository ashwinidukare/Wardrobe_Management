package db
import models.Wardrobe
import play.api.db.slick.HasDatabaseConfig
import slick.jdbc.JdbcProfile

trait DB extends HasDatabaseConfig[JdbcProfile]

trait WardrobeTable {
  this: DB =>
  import dbConfig.profile.api._

  class WardrobeTable(tag: Tag) extends Table[Wardrobe](tag, "wardrobe") {
    def id = column[String]("id", O.PrimaryKey)
    def name = column[String]("name")
    def category = column[String]("category")
    def * = (id, name, category) <> ({ data =>
    {
      Wardrobe(data._1, data._2, data._3)
    }
    },
      {
        r: Wardrobe =>
        {
          Some(r.id, r.name, r.category)
        }
      })
  }

  protected val wardrobe = TableQuery[WardrobeTable]
}