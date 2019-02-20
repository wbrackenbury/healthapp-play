package dao

import scala.concurrent.{ ExecutionContext, Future }
import javax.inject.Inject

import controllers.UserForm.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile

class UserDAO @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Users = TableQuery[UsersTable]

  def all(): Future[Seq[User]] = db.run(Users.result)

  def insert(user: User): Future[Unit] = db.run(Users += user).map { _ => () }

  private class UsersTable(tag: Tag) extends Table[User](tag, "users") {

    def uname = column[String]("username", O.PrimaryKey)
    def pass = column[String]("password")
    def salt = column[String]("salt")

    def * = (uname, pass, salt) <> (User.tupled, User.unapply)
  }
}
