package hulkdx.com.domain.exception

import java.lang.Exception

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/07/2019.
 */
class AuthException(message: String = ""): Exception("AuthException $message")

class InvalidUserException(message: String = ""): Exception("InvalidUserException $message")
