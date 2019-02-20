package controllers

import javax.inject.Inject

import play.api.data._
import play.api.mvc._
import play.api.i18n._

import dao.UserDAO
import models.User

import scala.concurrent.ExecutionContext

import slick.jdbc.DatabaseUrlDataSource

class HomeController @Inject()(
  cc: MessagesControllerComponents,
  messagesAction: MessagesActionBuilder,
  userDAO: UserDAO)(implicit ec: ExecutionContext)
    extends MessagesAbstractController(cc) {

  import UserForm._

  private val loginPostUrl = routes.HomeController.insert_user()

  def index() = Action { implicit request: MessagesRequest[AnyContent]  =>
    Ok(views.html.index(userForm, loginPostUrl))
  }

  def submit_form() = Action { implicit request: MessagesRequest[AnyContent]  =>
    Ok(views.html.submitform())
  }

  def insert_user() = Action.async { implicit request: MessagesRequest[AnyContent] =>

    val user: UserForm.User = userForm.bindFromRequest.get
    userDAO.insert(user).map(_ => Redirect(routes.HomeController.submit_form()))

  }

  def newest() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.newest())
  }

  def catalogue() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.catalogue())
  }

  def contact() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.contact())
  }


}




