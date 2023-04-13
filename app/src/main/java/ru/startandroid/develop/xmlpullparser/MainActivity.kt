package ru.startandroid.develop.xmlpullparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader

const val LOG_TAG = "myLogs"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tmp: String

        try {
            val xpp: XmlPullParser = prepareXpp()

            while (xpp.eventType != XmlPullParser.END_DOCUMENT) {
                when (xpp.eventType) {
                    XmlPullParser.START_DOCUMENT -> Log.d(LOG_TAG, "START_DOCUMENT")
                    XmlPullParser.START_TAG -> {
                        Log.d(LOG_TAG, "START_TAG: name = " + xpp.name
                                + ", depth = " + xpp.depth + ", attrCount = "
                                + xpp.attributeCount)
                        tmp = ""
                        var i = 0
                        while (i < xpp.attributeCount) {
                            tmp = (tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ")
                            i++
                        }
                        if (!TextUtils.isEmpty(tmp)) {
                            Log.d(LOG_TAG, "Attributes: $tmp")
                        }
                    }
                    XmlPullParser.END_TAG -> Log.d(LOG_TAG, "END_TAG: name = ${xpp.name}")
                    XmlPullParser.TEXT -> Log.d(LOG_TAG, "text = ${xpp.text}")
                }
                xpp.next()
            }
            Log.d(LOG_TAG, "END_DOCUMENT")
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }
    }
    private fun prepareXpp() : XmlPullParser {
        val factory: XmlPullParserFactory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val xpp: XmlPullParser = factory.newPullParser()
        xpp.setInput(StringReader("<data><phone><company>Samsung</company></phone></data>"))
        return xpp
    }
}