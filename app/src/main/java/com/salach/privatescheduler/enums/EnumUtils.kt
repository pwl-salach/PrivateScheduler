package com.salach.privatescheduler.enums

import android.content.res.Resources

class EnumUtils {
    companion object{
        inline fun <reified T : Enum<T>> getEnumStringResource(packageName: String, resources: Resources, enumVal: T): String{
            return resources.getString(resources.getIdentifier("enum_${enumVal.name}", "string", packageName))
        }
    }
}
