import org.xml.sax.SAXException
import parser.ParserDOM
import parser.ParserSAX
import parser.ParserStAX
import parser.Validator
import java.lang.Exception

/*
Создать файл XML и соответствующую ему схему XSD.
При разработке XSD использовать простые и комплексные типы, перечисления, шаблоны и предельные значения,
обязательно использование атрибутов и типа ID.
Сгенерировать (создать) Java-класс, соответствующий данному описанию.
Создать Java-приложение для разбора XML-документа и инициализации коллекции объектов информацией из XML-файла.
Для разбора использовать SAX, DOM и StAX парсеры. Для сортировки объектов использовать интерфейс Comparator.
Произвести проверку XML-документа с привлечением XSD.
Определить метод, производящий преобразование разработанного XML-документа в документ, указанный в каждом задании.
Избегать copy-past кода.
Весь код должен быть покрыт юнит тестами.

     13. Туристические путевки.

    Туристические путевки, предлагаемые агентством, имеют следующие характеристики:
    •	Type – тип путевки (выходного дня, экскурсионная, отдых, паломничество и т.д.).
    •	Country – страна для путешествия.
    •	Number days/nights – количество дней и ночей.
    •	Тransport – вид перевозки туристов (авиа, ж/д, авто, лайнер).
    •	Hotel characteristic (должно быть несколько) –
            количество звезд,
            включено ли питание и какое (HB, BB, Al),
            какой номер (1,2,3-х местные),
            есть ли телевизор,
            weeкондиционер и т.д..
    •	Сost – стоимость путевки (сколько и что включено).
    Корневой элемент назвать Тourist voucher.

 */

class App {
}

fun main() {
    println("Start")

    val xmlPath = "Tourist.xml"
    val xsdPath = "Tourist.xsd"

    try {
        println("=============VALIDATION===========")
        val validator = Validator()
        validator.validate(xmlPath, xsdPath)
        println("XML is valid.")
    } catch (ex: SAXException) {
        println("XML not valid because " + ex.message)
    }


    val parserDOM = ParserDOM()
    val parserSAX = ParserSAX()
    val parserStAX = ParserStAX()

//    println(a.checkXml(xmlPath, xsdPath))

    try {
        println("================DOM===============")
        val vouchers = parserDOM.parse(xmlPath)
        vouchers.forEach {
            println(it)
        }
    } catch (ex: Exception) {
        println(ex.message)
    }

    try {
        println("================SAX===============")
        val vouchers = parserSAX.parse(xmlPath)
        vouchers.forEach {
            println(it)
        }
    } catch (ex: Exception) {
        println(ex.message)
    }

    try {
        println("================StAX===============")
        val vouchers = parserStAX.parse(xmlPath)
        vouchers.forEach {
            println(it)
        }
    } catch (ex: Exception) {
        println(ex.message)
    }


    //    vouchers = vouchers.sortedBy { it.NumberOfDays }
//    vouchers.forEach {
//        println(it)
//    }


}