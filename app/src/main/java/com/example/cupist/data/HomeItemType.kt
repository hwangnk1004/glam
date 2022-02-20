package com.example.cupist.data

enum class HomeItemType(val typeCode: Int) {
    TODAY(1),
    TARGET_ITEM(2),
    TARGET_LAYOUT(3),
    ADDITIONAL(4);

    companion object {
        fun of(typeCode: Int): HomeItemType {
            return values().find { it.typeCode == typeCode }
                ?: throw IllegalStateException("invalid typeCode: $typeCode")
        }
    }
}