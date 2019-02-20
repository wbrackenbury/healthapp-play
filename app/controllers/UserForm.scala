package controllers

object UserForm {
  import play.api.data.Forms._
  import play.api.data.Form

  case class User(username: String, password: String, salt: String)

  import com.github.t3hnar.bcrypt._

  val safeApply = {(username: String, password: String, salt: String) =>

    val salt = generateSalt
    val encryptedPass = password.bcrypt(salt)
    User(username, encryptedPass, salt)
  }

  val mockUnapply = (user: User) => Some((user.username, user.password, ""))

  val userForm = Form(
    mapping(
      "Username" -> nonEmptyText,
      "Password" -> nonEmptyText,
      "csrfToken" -> nonEmptyText
    )(safeApply)(mockUnapply)
  )
}

