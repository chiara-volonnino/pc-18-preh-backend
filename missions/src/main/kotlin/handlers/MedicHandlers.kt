package handlers

import io.netty.handler.codec.http.HttpResponseStatus
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.get
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import handlers.Shared.MISSIONS_COLLECTION
import handlers.Shared.MONGODB_CONFIGURATION

object MedicHandlers {

    fun updateMedic(context: RoutingContext) {
        val response = context.response()
        val missionId: String = context.request().getParam("missionId")
        val medic: String = context.bodyAsString

        val query = json { obj("_id" to missionId) }
        val update = json { obj(
                "\$set" to obj("medic" to medic)
        ) }
        MongoClient.createNonShared(Main.vertx, MONGODB_CONFIGURATION)
                .updateCollection(MISSIONS_COLLECTION, query, update) { updateOperation ->
                    when {
                        updateOperation.failed() ->
                            response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end()
                        updateOperation.result().docMatched == 0L ->
                            response.setStatusCode(HttpResponseStatus.NOT_FOUND.code()).end()
                        else ->
                            response.setStatusCode(HttpResponseStatus.NO_CONTENT.code()).end()
                    }
                }
    }

    fun retrieveMedic(context: RoutingContext) {
        val response = context.response()
        val missionId: String = context.request().getParam("missionId")

        val query = json { obj("_id" to missionId) }
        MongoClient.createNonShared(Main.vertx, MONGODB_CONFIGURATION)
                .findOne(MISSIONS_COLLECTION, query, null) { findOneOperation ->
                    if (findOneOperation.failed()) {
                        response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end()
                    } else {
                        val result: JsonObject? = findOneOperation.result()
                        if (result == null) {
                            response.setStatusCode(HttpResponseStatus.NOT_FOUND.code()).end()
                        } else {
                            val medic: String? = result["medic"]
                            if (medic == null) {
                                response.setStatusCode(HttpResponseStatus.NO_CONTENT.code()).end()
                            } else {
                                response.putHeader("Content-Type", "text/plain")
                                        .setStatusCode(HttpResponseStatus.OK.code())
                                        .end(medic)
                            }
                        }
                    }
                }
    }

    fun deleteMedic(context: RoutingContext) {
        val response = context.response()
        val missionId: String = context.request().getParam("missionId")

        val query = json { obj("_id" to missionId) }
        val update = json { obj(
                "\$unset" to obj("medic" to 1)
        ) }
        MongoClient.createNonShared(Main.vertx, MONGODB_CONFIGURATION)
                .updateCollection(MISSIONS_COLLECTION, query, update) { updateOperation ->
                    when {
                        updateOperation.failed() ->
                            response.setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end()
                        updateOperation.result().docModified == 0L ->
                            response.setStatusCode(HttpResponseStatus.NOT_FOUND.code()).end()
                        else ->
                            response.setStatusCode(HttpResponseStatus.NO_CONTENT.code()).end()
                    }
                }
    }
}
