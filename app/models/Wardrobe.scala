package models

import play.api.libs.json.{Json, Reads, Writes}

case class Wardrobe(id: String, name: String, category: String)

object Wardrobe {
  implicit val reads: Reads[Wardrobe] = Json.reads[Wardrobe]
  implicit val writes: Writes[Wardrobe] = Json.writes[Wardrobe]
}