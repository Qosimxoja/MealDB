package uz.kosimkhujasharipov.mealdb.core.models.aboutUs

class SocialNetworkData(val name: String, val image: String) {
    companion object {
        fun socialNetworkData(): ArrayList<SocialNetworkData> {
            return arrayListOf(
                SocialNetworkData(
                    "Instagram",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Instagram-Icon.png/1200px-Instagram-Icon.png"
                ),
                SocialNetworkData(
                    "Facebook",
                    "https://www.facebook.com/images/fb_icon_325x325.png"
                ),
                SocialNetworkData(
                    "LinkedIn",
                    "https://sp-ao.shortpixel.ai/client/to_webp,q_glossy,ret_img,w_1200,h_1200/https://tatajinnovation.com/wp-content/uploads/2022/08/LinkedIn_icon_circle.svg.png"
                ),
                SocialNetworkData(
                    "Email",
                    "https://help.apple.com/assets/61DC8A1AC689DE6B7F17E3B8/61DC8A1BC689DE6B7F17E3BF/en_GB/e4dbb8e240d50cf30bab73b272a3760b.png"
                ),
            )
        }

        fun sourceData(): ArrayList<SocialNetworkData> {
            return arrayListOf(
                SocialNetworkData(
                    "GitHub",
                    "https://play-lh.googleusercontent.com/PCpXdqvUWfCW1mXhH1Y_98yBpgsWxuTSTofy3NGMo9yBTATDyzVkqU580bfSln50bFU"
                ),
                SocialNetworkData(
                    "GitLab",
                    "https://gitlab.com/uploads/-/system/project/avatar/20237268/logo-extra-whitespace.png"
                ),
            )
        }
    }
}