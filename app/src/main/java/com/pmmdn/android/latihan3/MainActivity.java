package com.pmmdn.android.latihan3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pmmdn.android.latihan3.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Hero> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rvHeroes.setHasFixedSize(true);

        list.addAll(HeroesData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        binding.rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        binding.rvHeroes.setAdapter(listHeroAdapter);

        listHeroAdapter.setOnItemClickCallback(data -> {
            // intent, startActivity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("heroName", data.getName());
            intent.putExtra("heroDesc", data.getDesc());
            intent.putExtra("heroImage", data.getImage());
            startActivity(intent);
        });
    }

    // grid view
    private void showRecyclerGrid(){
        binding.rvHeroes.setLayoutManager(new GridLayoutManager(this, 2));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        binding.rvHeroes.setAdapter(gridHeroAdapter);

        gridHeroAdapter.setOnItemClickCallback(data -> {
            // intent, startActivity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("heroName", data.getName());
            intent.putExtra("heroDesc", data.getDesc());
            intent.putExtra("heroImage", data.getImage());
            startActivity(intent);
        });
    }

    // card view
    private void showRecyclerCardView() {
        binding.rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardViewAdapter cardViewHeroAdapter = new CardViewAdapter(list);
        binding.rvHeroes.setAdapter(cardViewHeroAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                showRecyclerList();
                break;
            case R.id.action_grid:
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                showRecyclerCardView();
                break;
        }
    }
}