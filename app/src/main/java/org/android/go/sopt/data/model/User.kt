package org.android.go.sopt.data.model

class User {
    companion object{
        private var id = ""

        fun getId():String{
            return id
        }

        fun setId(id:String){
            this.id = id
        }

    }
}