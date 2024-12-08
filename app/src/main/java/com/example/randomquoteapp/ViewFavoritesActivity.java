package com.example.randomquoteapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewFavoritesActivity extends AppCompatActivity {

    private TextView favoriteQuoteTextView, favoriteAuthorTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favorites);

        // Initialize UI components
        favoriteQuoteTextView = findViewById(R.id.favoriteQuoteTextView);
        favoriteAuthorTextView = findViewById(R.id.favoriteAuthorTextView);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("favorite_quotes", Context.MODE_PRIVATE);

        // Display the saved favorite quote and author
        String favoriteQuote = sharedPreferences.getString("favorite_quote", "No favorite quote saved.");
        String favoriteAuthor = sharedPreferences.getString("favorite_author", "");

        favoriteQuoteTextView.setText(favoriteQuote);
        favoriteAuthorTextView.setText(favoriteAuthor);
    }
}