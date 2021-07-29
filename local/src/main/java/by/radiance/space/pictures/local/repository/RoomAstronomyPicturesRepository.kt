package by.radiance.space.pictures.local.repository

import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.repository.saved.SavedAstronomyPicturesRepository
import by.radiance.space.pictures.local.entity.PictureDAO
import java.sql.Date

class RoomAstronomyPicturesRepository(
    private val pictureDAO: PictureDAO
): SavedAstronomyPicturesRepository {
    override suspend fun getSavedPictures(): List<by.radiance.space.pictures.domain.entity.Picture> {
        return pictureDAO.getAll().map {
            by.radiance.space.pictures.domain.entity.Picture(
                    id = Id(it.id),
                    title = it.title?:"",
                    explanation = it.explanation?:"",
                    copyright = it.copyright,
                    source = Image(huge = it.hsrc?:"", light = it.src?:""),
                    isSaved = true
            )
        }
//        return listOf(
//            AstronomyPicture(id = PictureId(getDate("1997-09-06")), title = "Isaac Newton Explains the Solar System", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/IsaacNewton_big.jpg", light = "https://apod.nasa.gov/apod/image/IsaacNewton.gif"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("2002-03-08")), title = "Columbia Dawn", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/0203/columbiadawn_barrett_big.jpg", light = "https://apod.nasa.gov/apod/image/0203/columbiadawn_barrett.jpg"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("2017-06-04")), title = "Orion: Belt, Flame, and Horsehead", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/1706/orionbfh_andreo_1089.jpg", light = "https://apod.nasa.gov/apod/image/1706/orionbfh_andreo_960.jpg"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("2002-05-05")), title = "The M7 Open Star Cluster in Scorpius", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/0004/m7_noao_big.jpg", light = "https://apod.nasa.gov/apod/image/0004/m7_noao.jpg"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("2016-06-06")), title = "The Supernova and Cepheids of Spiral Galaxy UGC 9391", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/1606/UGC9391_hubble_2800_plain.jpg", light = "https://apod.nasa.gov/apod/image/1606/UGC9391_hubble_960_annotated.jpg"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("1997-06-24")), title = "Antares", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/9706/antaresneb_uks.gif", light = "https://apod.nasa.gov/apod/image/9706/antaresneb_uks_big.jpg"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("2017-10-16")), title = "GW170817: A Spectacular Multi-Radiation Merger Event Detected", explanation = "", copyright = "", source = Image(huge ="https://img.youtube.com/vi/x_Akn8fUBeQ/0.jpg", light = "https://img.youtube.com/vi/x_Akn8fUBeQ/0.jpg"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("2017-05-26")), title = "Spiral Galaxy NGC 6744", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/1705/ngc6744-maximumVerschatse.jpg", light = "https://apod.nasa.gov/apod/image/1705/ngc6744-1024Verschatse.jpg"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("2000-01-15")), title = "The Sun Also Rises", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/0001/sunrise_sts47.gif", light = "https://apod.nasa.gov/apod/image/0001/sunrise_sts47.gif"), isSaved = false),
//            AstronomyPicture(id = PictureId(getDate("1998-12-17")), title = "The Night Shift", explanation = "", copyright = "", source = Image(huge ="https://apod.nasa.gov/apod/image/9812/nightwork_sts88_big1.jpg", light = "https://apod.nasa.gov/apod/image/9812/nightwork_sts88_crop1.jpg"), isSaved = false),
//        )
    }

    override suspend fun getSavedPictureById(id: Id): by.radiance.space.pictures.domain.entity.Picture? {
        return pictureDAO.getPicture(Date(id.date.time)).firstOrNull()?.let {
            by.radiance.space.pictures.domain.entity.Picture(
                    id = Id(it.id),
                    title = it.title?:"",
                    explanation = it.explanation?:"",
                    copyright = it.copyright,
                    source = Image(huge = it.hsrc?:"", light = it.src?:""),
                    isSaved = true
            )
        }
    }

    override suspend fun save(picture: by.radiance.space.pictures.domain.entity.Picture): by.radiance.space.pictures.domain.entity.Picture {
        return pictureDAO.insert(Picture(
            id = Date(picture.id.date.time),
            title = picture.title,
            explanation = picture.explanation,
            copyright = picture.copyright?:"",
            src = if(picture.source is Image) (picture.source as Image).light else "",
            hsrc = if(picture.source is Image) (picture.source as Image).huge else "",
        )).let {
            picture
        }
    }

    override suspend fun delete(picture: by.radiance.space.pictures.domain.entity.Picture) {
        pictureDAO.delete(Date(picture.id.date.time))
    }


}