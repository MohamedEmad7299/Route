package com.ug.route.data.fake

import com.ug.route.R
import com.ug.route.networking.dto_models.cart_items.CartItem
import com.ug.route.networking.dto_models.categories.Category
import com.ug.route.networking.dto_models.products.Product
import com.ug.route.networking.dto_models.sub_categories.SubCategory
import com.ug.route.networking.dto_models.wish_list.WishListItem

object FakeData{

    var categories: List<Category> = emptyList()

    var subCategories : List<SubCategory> = emptyList()

    var cartItems : List<CartItem> = emptyList()

    var wishList : List<WishListItem?> = emptyList()

    var products: List<Product> = emptyList()

    const val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0MDdjZjZmNTE1YmRjZjM0N2MwOWYxNyIsIm5hbWUiOiJBaG1lZCBBYmQgQWwtTXV0aSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNzE2NTk5MzU3LCJleHAiOjE3MjQzNzUzNTd9.NgL0zySmcUHyx0HCw7YLSURb1_j_gK1bVH5cdt77Sg8"

    val subCategoryImages: Map< String , Int> = mapOf(

        "6407f0bfb575d3b90bf95776" to R.drawable.clean_products,
        "6407f0cbb575d3b90bf95779" to R.drawable.health_nutrition,
        "6407f11eb575d3b90bf9577c" to R.drawable.home_appliances,
        "6407f131b575d3b90bf9577f" to R.drawable.large_appliance,
        "6407f13cb575d3b90bf95782" to R.drawable.cookware,
        "6407f149b575d3b90bf95785" to R.drawable.drinkware,
        "6407f154b575d3b90bf95788" to R.drawable.bedding,
        "6407f163b575d3b90bf9578b" to R.drawable.tools,
        "6407f17cb575d3b90bf9578e" to R.drawable.furniture,
        "6407f188b575d3b90bf95791" to R.drawable.home_decor,
        "6407f198b575d3b90bf95794" to R.drawable.kitchen,
        "6407f1bcb575d3b90bf95797" to R.drawable.dress,
        "6407f1cbb575d3b90bf9579a" to R.drawable.womens_footwear,
        "6407f1e1b575d3b90bf9579d" to R.drawable.womens_watches,
        "6407f1ecb575d3b90bf957a0" to R.drawable.jewellery,
        "6407f1fdb575d3b90bf957a3" to R.drawable.womens_eyewear,
        "6407f208b575d3b90bf957a6" to R.drawable.womens_bag,
        "6407f219b575d3b90bf957a9" to R.drawable.baby_fashion,
        "6407f243b575d3b90bf957ac" to R.drawable.suits,
        "6407f276b575d3b90bf957b8" to R.drawable.bag,
        "6407f2ddb575d3b90bf957be" to R.drawable.mobile_n,
        "6407f2f7b575d3b90bf957c4" to R.drawable.huawei,
        "6407f305b575d3b90bf957c7" to R.drawable.tablets,
        "6407f313b575d3b90bf957ca" to R.drawable.smartwatches,
        "6407f320b575d3b90bf957cd" to R.drawable.w_earphones,
        "6407f32eb575d3b90bf957d0" to R.drawable.earphones,
        "6407f33ab575d3b90bf957d3" to R.drawable.power_banks,
        "6407f34bb575d3b90bf957d6" to R.drawable.charger,
        "6407f360b575d3b90bf957d9" to R.drawable.covers,
        "6407f36db575d3b90bf957dc" to R.drawable.vr,
        "6407f39bb575d3b90bf957df" to R.drawable.tv,
        "6407f3a8b575d3b90bf957e2" to R.drawable.laptops,
        "6407f3b4b575d3b90bf957e5" to R.drawable.audio,
        "6407f3c0b575d3b90bf957e8" to R.drawable.video_games,
        "6407f3ccb575d3b90bf957eb" to R.drawable.cameras,
        "6407f3d8b575d3b90bf957ee" to R.drawable.printers_accessories,
        "6407f3e3b575d3b90bf957f1" to R.drawable.networking_products,
        "6407f3f6b575d3b90bf957f4" to R.drawable.data_storage,
        "6407f402b575d3b90bf957f7" to R.drawable.computer_components,
        "6407f40db575d3b90bf957fa" to R.drawable.computer_accessories
    )

    val categoryImages: Map< String , Int> = mapOf(

        "Men's Fashion" to R.drawable.mens_fashion,
        "Women's Fashion" to R.drawable.womens_fashion,
        "Music" to R.drawable.music,
        "SuperMarket" to R.drawable.supermarket,
        "Baby & Toys" to R.drawable.baby,
        "Home" to R.drawable.home0,
        "Books" to R.drawable.books,
        "Beauty & Health" to R.drawable.beauty,
        "Mobiles" to R.drawable.mobiles,
        "Electronics" to R.drawable.electronics
    )
}