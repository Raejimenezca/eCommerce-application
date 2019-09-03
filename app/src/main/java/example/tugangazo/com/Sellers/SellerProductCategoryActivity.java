package example.tugangazo.com.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import example.tugangazo.com.R;

public class SellerProductCategoryActivity extends AppCompatActivity {
    private ImageView phoneProducts, tools, musicInstruments, petsProducts;
    private ImageView clothes, sportClothes, femaleShoes, feedingProducts;
    private ImageView homeProducts, beautyProducts, lingerie, footwear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_category);


        phoneProducts = (ImageView) findViewById(R.id.phone_accesories);
        tools = (ImageView) findViewById(R.id.tools);
        musicInstruments = (ImageView) findViewById(R.id.music_instruments);
        petsProducts = (ImageView) findViewById(R.id.pets_products);

        clothes = (ImageView) findViewById(R.id.clothes);
        sportClothes = (ImageView) findViewById(R.id.sports_clothes);
        femaleShoes = (ImageView) findViewById(R.id.female_shoes);
        feedingProducts = (ImageView) findViewById(R.id.feeding_products);

        homeProducts = (ImageView) findViewById(R.id.home_products);
        beautyProducts = (ImageView) findViewById(R.id.beauty_products);
        lingerie = (ImageView) findViewById(R.id.lingerie);
        footwear = (ImageView) findViewById(R.id.footwear);


        phoneProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Accesorios para celular");
                startActivity(intent);
            }
        });

        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Herramientas");
                startActivity(intent);
            }
        });

        musicInstruments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Instrumentos musicales");
                startActivity(intent);
            }
        });

        petsProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Productos para mascotas");
                startActivity(intent);
            }
        });

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Ropa");
                startActivity(intent);
            }
        });

        sportClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Ropa deportiva");
                startActivity(intent);
            }
        });

        femaleShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Calzado para dama");
                startActivity(intent);
            }
        });

        feedingProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Productos alimenticios");
                startActivity(intent);
            }
        });

        homeProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Articulos para el hogar");
                startActivity(intent);
            }
        });

        beautyProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Productos de belleza");
                startActivity(intent);
            }
        });

        lingerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Lenceria");
                startActivity(intent);
            }
        });

        footwear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category", "Calzado");
                startActivity(intent);
            }
        });
    }
}
