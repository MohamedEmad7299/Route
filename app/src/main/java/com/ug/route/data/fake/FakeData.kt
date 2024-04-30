package com.ug.route.data.fake

import com.ug.route.R
import com.ug.route.data.database.entities.ProductEntity

object FakeData {

    val products = mutableListOf(
        "T-shirts",
        "Jeans",
        "Pants",
        "Footwear",
        "Suits",
        "Watches",
        "Bags",
        "Eyewears",
        "Dresses",
        "Skirts",
        "Pyjamas",
        "Women's Bags",
        "Women's Jeans",
        "Women's T-shirts",
        "Women's Footwear",
        "Women's Eyewear",
        "Women's Watches",
        "Drums",
        "Violin",
        "Guitar",
        "Piano",
        "Saxophone",
        "Flute",
        "Trombone",
        "Harp",
        "Accordion",
        "Canned Food",
        "Fruits",
        "Milk Products",
        "Soft Drinks",
        "Clean Products",
        "Oils",
        "Seeds",
        "Meat",
        "Spices",
        "Baby Clothes",
        "Baby Footwear",
        "Toys",
        "Diaper",
        "Beds",
        "Baby Books",
        "Furniture",
        "Home Appliance",
        "Carpets",
        "Comedy Books",
        "Cooking Books",
        "Science Books",
        "Skincare",
        "Mackup",
        "Xiaomi",
        "Huawei",
        "Iphone",
        "Laptops",
        "iPads",
        "Cameras"
    )

    val categoryImages: Map< String , Int> = mapOf(

        "Men's Fashion" to R.drawable.mens_fashion,
        "Women's Fashion" to R.drawable.womens_fashion,
        "Music" to R.drawable.music,
        "Super Market" to R.drawable.supermarket,
        "Baby & Toys" to R.drawable.baby,
        "Home" to R.drawable.home0,
        "Books" to R.drawable.books,
        "Beauty & Health" to R.drawable.beauty,
        "Mobiles" to R.drawable.mobiles,
        "Electronics" to R.drawable.electronics
    )

    val subCategories: Map<String, List<Subcategory>> = mapOf(

        "Men's Fashion" to listOf(
            Subcategory(
                name = "T-shirts",
                imageResource = R.drawable.tshirts
            ),
            Subcategory(
                name = "Shorts",
                imageResource = R.drawable.shorts
            ),
            Subcategory(
                name = "Jeans",
                imageResource = R.drawable.jeanz
            ),
            Subcategory(
                name = "Pants",
                imageResource = R.drawable.pants
            ),
            Subcategory(
                name = "Footwear",
                imageResource = R.drawable.footwear
            ),
            Subcategory(
                name = "Suits",
                imageResource = R.drawable.suits
            ),
            Subcategory(
                name = "Watches",
                imageResource = R.drawable.watches
            ),
            Subcategory(
                name = "Bags",
                imageResource = R.drawable.bags
            ),
            Subcategory(
                name = "Eyewears",
                imageResource = R.drawable.eyewear
            )
        ),
        "Women's Fashion" to listOf(
            Subcategory(
                name = "Dresses",
                imageResource = R.drawable.dress
            ),
            Subcategory(
                name = "Jeans",
                imageResource = R.drawable.womens_jeans
            ),
            Subcategory(
                name = "Skirts",
                imageResource = R.drawable.skirt
            ),
            Subcategory(
                name = "Pyjamas",
                imageResource = R.drawable.pjama
            ),
            Subcategory(
                name = "Bags",
                imageResource = R.drawable.womens_bag
            ),
            Subcategory(
                name = "T-shirts",
                imageResource = R.drawable.womens_tshirt
            ),
            Subcategory(
                name = "Footwear",
                imageResource = R.drawable.womens_footwear
            ),
            Subcategory(
                name = "Eyewear",
                imageResource = R.drawable.womens_eyewear
            ),
            Subcategory(
                name = "Watches",
                imageResource = R.drawable.womens_watches
            )
        ),
        "Music" to listOf(
            Subcategory(
                name = "Drums",
                imageResource = R.drawable.drums
            ),
            Subcategory(
                name = "Violin",
                imageResource = R.drawable.violin
            ),
            Subcategory(
                name = "Guitar",
                imageResource = R.drawable.guitar
            ),
            Subcategory(
                name = "Piano",
                imageResource = R.drawable.piano
            ),
            Subcategory(
                name = "Saxophone",
                imageResource = R.drawable.saxophone
            ),
            Subcategory(
                name = "Flute",
                imageResource = R.drawable.flute
            ),
            Subcategory(
                name = "Trombone",
                imageResource = R.drawable.trombone
            ),
            Subcategory(
                name = "Harp",
                imageResource = R.drawable.harpist
            ),
            Subcategory(
                name = "Accordion",
                imageResource = R.drawable.accordion
            )
        ),
        "Super Market" to listOf(
            Subcategory(
                name = "Canned Food",
                imageResource = R.drawable.canned_food
            ),
            Subcategory(
                name = "Fruits",
                imageResource = R.drawable.fruits
            ),
            Subcategory(
                name = "Milk Products",
                imageResource = R.drawable.milk_products
            ),
            Subcategory(
                name = "Soft Drinks",
                imageResource = R.drawable.soft_drinks
            ),
            Subcategory(
                name = "Clean Products",
                imageResource = R.drawable.clean_products
            ),
            Subcategory(
                name = "Oils",
                imageResource = R.drawable.oils
            ),
            Subcategory(
                name = "Seeds",
                imageResource = R.drawable.seeds
            ),
            Subcategory(
                name = "Meat",
                imageResource = R.drawable.meat
            ),
            Subcategory(
                name = "Spices",
                imageResource = R.drawable.spices
            )
        ),
        "Baby & Toys" to listOf(
            Subcategory(
                name = "Clothes",
                imageResource = R.drawable.baby_fashion
            ),
            Subcategory(
                name = "Footwear",
                imageResource = R.drawable.baby_footwear
            ),
            Subcategory(
                name = "Toys",
                imageResource = R.drawable.toys
            ),
            Subcategory(
                name = "Diaper",
                imageResource = R.drawable.diaper
            ),
            Subcategory(
                name = "Beds",
                imageResource = R.drawable.baby_beed
            ),
            Subcategory(
                name = "Books",
                imageResource = R.drawable.baby_books
            )
        ),
        "Home" to listOf(
            Subcategory(
                name = "Furniture",
                imageResource = R.drawable.furniture
            ),
            Subcategory(
                name = "Home Appliance",
                imageResource = R.drawable.home_appliance
            ),
            Subcategory(
                name = "Carpets",
                imageResource = R.drawable.carpets
            )
        ),
        "Books" to listOf(
            Subcategory(
                name = "Comedy Books",
                imageResource = R.drawable.comedy_books
            ),
            Subcategory(
                name = "Cooking Books",
                imageResource = R.drawable.cooking_books
            ),
            Subcategory(
                name = "Science Books",
                imageResource = R.drawable.science_books
            )
        ),
        "Beauty & Health" to listOf(
            Subcategory(
                name = "Skincare",
                imageResource = R.drawable.skincare
            ),
            Subcategory(
                name = "Mackup",
                imageResource = R.drawable.mackup
            )
        ),
        "Mobiles" to listOf(
            Subcategory(
                name = "Xiaomi",
                imageResource = R.drawable.xiaomi
            ),
            Subcategory(
                name = "Huawei",
                imageResource = R.drawable.huawei
            ),
            Subcategory(
                name = "Iphone",
                imageResource = R.drawable.iphone
            )
        ),
        "Electronics" to listOf(
            Subcategory(
                name = "Laptops",
                imageResource = R.drawable.laptops
            ),
            Subcategory(
                name = "iPads",
                imageResource = R.drawable.ipad
            ),
            Subcategory(
                name = "Cameras",
                imageResource = R.drawable.cameras
            )
        ),
    )

    val laptops = mutableListOf(
        ProductEntity(
            id = 0,
            isFavourite = false,
            imageResource = R.drawable.hp,
            name = "HP OMEN 17t-cm200 - Gaming & Entertainment Laptop",
            price = 56532,
            review = "4.4"
        ),
        ProductEntity(
            id = 0,
            isFavourite = false,
            imageResource = R.drawable.dell,
            name = "Inspiron 15 Laptop - NVIDIA® GeForce RTX™ 4060 32 GB DDR5",
            price = 66532,
            review = "4.5"
        ),
        ProductEntity(
            id = 0,
            isFavourite = false,
            imageResource = R.drawable.razer,
            name = "Razer Blade 14 - AMD Ryzen™ 9 8945HS",
            price = 53432,
            review = "4.9"
        ),
        ProductEntity(
            id = 0,
            isFavourite = false,
            imageResource = R.drawable.lenvo,
            name = "Legion Slim 7i Gen 8 Intel (16″) - RTX 4060",
            price = 51000,
            review = "4.8"
        ),
        ProductEntity(
            id = 0,
            isFavourite = false,
            imageResource = R.drawable.msi,
            name = "MSI GF63 - RTX 3050 4GB DDR6",
            price = 58532,
            review = "4.7"
        ),
        ProductEntity(
            id = 0,
            isFavourite = false,
            imageResource = R.drawable.asus,
            name = "ROG Flow Z13 Gaming Laptop",
            price = 56532,
            review = "4.3"
        ),
    )
}

data class Subcategory(
    val name: String,
    val imageResource: Int
)