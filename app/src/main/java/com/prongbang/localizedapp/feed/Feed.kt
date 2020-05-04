package com.prongbang.localizedapp.feed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feed(
		var id: Int = 0,
		var title: String = "",
		var description: String = "",
		var imageUrl: String = ""
): Parcelable {
	companion object {
		fun asListTH(): List<Feed> {
			return listOf(
					Feed(id = 1,
							title = "[Flutter] จัดการ State ด้วย BloC Pattern + Clean Architecture แบบไม่เน้นทฤษฎี",
							description = "ชื่อเต็มคือ Business Logic Component เพื่อจัดการกับ State ภายในแอพซึ่งข้อมูลจะวิ้งไหลผ่าน Rx และ Stream",
							imageUrl = "https://prongbang.github.io/assets/images/flutter/05.png"),
					Feed(id = 2,
							title = "[Golang] มาลองใช้ Wiremock ที่เขียนด้วย Golang สำหรับทำ Mock API โดยเขียนแค่ YAML และ JSON แค่นั้นเอง",
							description = "เคยเจอกับตัวกันบ้างมั้ยว่ากว่า API จะทำเสร็จก็กลาง Sprint แล้ว ฉะนั้นเรามาใช้ Wiremock เพื่อให้เราสามารถ Dev รอ API ได้กันเถอะ ๆ",
							imageUrl = "https://prongbang.github.io/assets/images/golang/10.png"),
					Feed(id = 3,
							title = "[Flutter] มาใช้ GetIt เพื่อช่วยทำ Service Locator กันเถอะ",
							description = "เพื่อนำมาแก้ปัญหาการสร้าง Instance ที่ซ้ำซ้อนกัน และทำให้การใช้งานได้ง่ายยิ่งขึ้น มาดูกันว่าต้องทำอย่างไร",
							imageUrl = "https://prongbang.github.io/assets/images/flutter/04.png"),
					Feed(id = 4,
							title = "[Golang] มาใช้ Wire เพื่อช่วยทำ Dependency Injection กันเถอะ",
							description = "บล็อคนี้ไม่อยากพิมพ์เยอะ เพราะวิธีใช้มันน้อย และง่ายมาก ๆ",
							imageUrl = "https://prongbang.github.io/assets/images/golang/9.png"),
					Feed(id = 5,
							title = "[Golang] มาดูว่าเขียน API ด้วย Golang ที่ Deploy บน Docker ว่าจะต้องใช้ทรัพยากรเครื่องไปเท่าไร เมื่อเทียบกับ Ktor ที่เขียนด้วย Kotlin",
							description = "เพื่อเป็นทางเลือกให้กับใครหลาย ๆ คนตัดสินใจได้ง่ายขึ้น ว่าถ้างบมีน้อยควรเก็บอะไรไว้เป็นตัวเลือกบ้าง",
							imageUrl = "https://prongbang.github.io/assets/images/golang/8.png")
			)
		}
		fun asListEN(): List<Feed> {
			return listOf(
					Feed(id = 1,
							title = "[Flutter] Manage your status with non-theoretical BloC Pattern + Clean Architecture.",
							description = "The full name is the Business Logic Component to manage the state within the app, which data will flow through Rx and Stream.",
							imageUrl = "https://prongbang.github.io/assets/images/flutter/05.png"),
					Feed(id = 2,
							title = "[Golang] Let's try using Wiremock written in Golang for Mock API. Only write YAML and JSON.",
							description = "Have you ever met before that the API will be completed in the middle of Sprint? So let's use Wiremock so we can Dev wait for the API.",
							imageUrl = "https://prongbang.github.io/assets/images/golang/10.png"),
					Feed(id = 3,
							title = "[Flutter] Let's use GetIt to help with Service Locator.",
							description = "In order to solve the problem of duplicate Instance creation And make operation easier Let's see what to do.",
							imageUrl = "https://prongbang.github.io/assets/images/flutter/04.png"),
					Feed(id = 4,
							title = "[Golang] Let's use Wire to help with Dependency Injection.",
							description = "This block doesn't want to type a lot. Because the method is very small and easy to use",
							imageUrl = "https://prongbang.github.io/assets/images/golang/9.png"),
					Feed(id = 5,
							title = "[Golang] Let's see how to write the API with Golang at Deploy on the Docker. How much machine resources are needed. Compared to Ktor written by Kotlin",
							description = "As an alternative for many people to make decisions easier That if there are small budgets, what should be kept as options?",
							imageUrl = "https://prongbang.github.io/assets/images/golang/8.png")
			)
		}
	}
}