package com.heinnovator.koenawin.data

import com.heinnovator.koenawin.data.Constants.benefits1
import com.heinnovator.koenawin.data.Constants.benefits2
import com.heinnovator.koenawin.data.Constants.benefits3
import com.heinnovator.koenawin.data.Constants.benefits4
import com.heinnovator.koenawin.data.Constants.benefits5
import com.heinnovator.koenawin.data.Constants.benefits6
import com.heinnovator.koenawin.data.Constants.benefits7
import com.heinnovator.koenawin.data.Constants.benefits8
import com.heinnovator.koenawin.data.Constants.benefits9
import com.heinnovator.koenawin.data.Constants.item1
import com.heinnovator.koenawin.data.Constants.item1Desc
import com.heinnovator.koenawin.data.Constants.item2
import com.heinnovator.koenawin.data.Constants.item2Desc
import com.heinnovator.koenawin.data.Constants.item3
import com.heinnovator.koenawin.data.Constants.item3Desc
import com.heinnovator.koenawin.data.Constants.item4
import com.heinnovator.koenawin.data.Constants.item4Desc
import com.heinnovator.koenawin.data.Constants.item5
import com.heinnovator.koenawin.data.Constants.item5Desc
import com.heinnovator.koenawin.data.Constants.item6
import com.heinnovator.koenawin.data.Constants.item6Desc
import com.heinnovator.koenawin.data.Constants.item7
import com.heinnovator.koenawin.data.Constants.item7Desc
import com.heinnovator.koenawin.data.Constants.item8
import com.heinnovator.koenawin.data.Constants.item8Desc
import com.heinnovator.koenawin.data.Constants.item9
import com.heinnovator.koenawin.data.Constants.item9Desc

object Repo {

    fun getBenefits(level: Int): String{
        return when (level){
            1 -> benefits1
            2 -> benefits2
            3 -> benefits3
            4 -> benefits4
            5 -> benefits5
            6 -> benefits6
            7 -> benefits7
            8 -> benefits8
            else -> benefits9
        }
    }

    fun getList(): List<Item>{
        return listOf(
            Item(1,item2,item2Desc,216, 1, 2),
            Item(2,item9,item9Desc,972, 1, 3),
            Item(3,item4,item4Desc,432, 1, 4),
            Item(4,item7,item7Desc,756, 1, 5),
            Item(5,item5,item5Desc,540, 1, 6),
            Item(6,item3,item3Desc,324, 1, 7),
            Item(7,item6,item6Desc,648, 1, 1),
            Item(8,item1,item1Desc,108, 1, 2),
            Item(9,item8,item8Desc,864, 1, 3),

            Item(10,item3,item3Desc,324, 2, 4),
            Item(11,item1,item1Desc,108, 2, 5),
            Item(12,item5,item5Desc,540, 2, 6),
            Item(13,item8,item8Desc,864, 2, 7),
            Item(14,item6,item6Desc,648, 2, 1),
            Item(15,item4,item4Desc,432, 2, 2),
            Item(16,item7,item7Desc,756, 2, 3),
            Item(17,item2,item2Desc,216, 2, 4),
            Item(18,item9,item9Desc,972, 2, 5),

            Item(19,item4,item4Desc,432, 3, 6),
            Item(20,item2,item2Desc,216, 3, 7),
            Item(21,item6,item6Desc,648, 3, 1),
            Item(22,item9,item9Desc,972, 3, 2),
            Item(23,item7,item7Desc,756, 3, 3),
            Item(24,item5,item5Desc,540, 3, 4),
            Item(25,item8,item8Desc,864, 3, 5),
            Item(26,item3,item3Desc,324, 3, 6),
            Item(27,item1,item1Desc,108, 3, 7),

            Item(28,item5,item5Desc,540, 4, 1),
            Item(29,item3,item3Desc,324, 4, 2),
            Item(30,item7,item7Desc,756, 4, 3),
            Item(31,item1,item1Desc,108, 4, 4),
            Item(32,item8,item8Desc,864, 4, 5),
            Item(33,item6,item6Desc,648, 4, 6),
            Item(34,item9,item9Desc,972, 4, 7),
            Item(35,item4,item4Desc,432, 4, 1),
            Item(36,item2,item2Desc,216, 4, 2),

            Item(37,item6,item6Desc,648,5,3),
            Item(38,item4,item4Desc,432,5,4),
            Item(39,item8,item8Desc,864,5,5),
            Item(40,item2,item2Desc,216,5,6),
            Item(41,item9,item9Desc,972,5,7),
            Item(42,item7,item7Desc,756,5,1),
            Item(43,item1,item1Desc,108,5,2),
            Item(44,item5,item5Desc,540,5,3),
            Item(45,item3,item3Desc,324,5,4),

            Item(46,item7,item7Desc,756,6,5),
            Item(47,item5,item5Desc,540,6,6),
            Item(48,item9,item9Desc,972,6,7),
            Item(49,item3,item3Desc,324,6,1),
            Item(50,item1,item1Desc,108,6,2),
            Item(51,item8,item8Desc,864,6,3),
            Item(52,item2,item2Desc,216,6,4),
            Item(53,item6,item6Desc,648,6,5),
            Item(54,item4,item4Desc,432,6,6),

            Item(55,item8,item8Desc,864,7,7),
            Item(56,item6,item6Desc,648,7,1),
            Item(57,item1,item1Desc,108,7,2),
            Item(58,item4,item4Desc,432,7,3),
            Item(59,item2,item2Desc,216,7,4),
            Item(60,item9,item9Desc,972,7,5),
            Item(61,item3,item3Desc,324,7,6),
            Item(62,item7,item7Desc,756,7,7),
            Item(63,item5,item5Desc,540,7,1),

            Item(64,item9,item9Desc,972,8,2),
            Item(65,item7,item7Desc,756,8,3),
            Item(66,item2,item2Desc,216,8,4),
            Item(67,item5,item5Desc,540,8,5),
            Item(68,item3,item3Desc,324,8,6),
            Item(69,item1,item1Desc,108,8,7),
            Item(70,item4,item4Desc,432,8,1),
            Item(71,item8,item8Desc,864,8,2),
            Item(72,item6,item6Desc,648,8,3),

            Item(73,item1,item1Desc,108,9,4),
            Item(74,item8,item8Desc,864,9,5),
            Item(75,item3,item3Desc,324,9,6),
            Item(76,item6,item6Desc,648,9,7),
            Item(77,item4,item4Desc,432,9,1),
            Item(78,item2,item2Desc,216,9,2),
            Item(79,item5,item5Desc,540,9,3),
            Item(80,item9,item9Desc,972,9,4),
            Item(81,item7,item7Desc,756,9,5)
        )
    }
}