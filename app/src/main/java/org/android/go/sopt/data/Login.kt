package org.android.go.sopt.data

class Login {
    companion object{
        private var id = ""
        private var pwd = ""

        fun getId():String{
            return id
        }

        fun getPwd():String{
            return pwd
        }

        fun setId(id:String){
            this.id = id
        }

        fun setPwd(pwd:String){
            this.pwd= pwd
        }
    }
}