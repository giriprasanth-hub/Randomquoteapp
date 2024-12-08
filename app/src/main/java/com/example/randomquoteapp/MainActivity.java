package com.example.randomquoteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView, authorTextView;
    private Button newQuoteButton, saveQuoteButton, shareQuoteButton, viewFavoritesButton;

    private List<String> quotes;
    private List<String> authors;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        quoteTextView = findViewById(R.id.quoteTextView);
        authorTextView = findViewById(R.id.authorTextView);
        newQuoteButton = findViewById(R.id.newQuoteButton);
        saveQuoteButton = findViewById(R.id.saveQuoteButton);
        shareQuoteButton = findViewById(R.id.shareQuoteButton);
        viewFavoritesButton = findViewById(R.id.viewFavoritesButton);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("favorite_quotes", Context.MODE_PRIVATE);

        // Initialize the quotes list
        quotes = new ArrayList<>();
        authors = new ArrayList<>();
        populateQuotes();

        // Display a random quote when the app starts
        showRandomQuote();

        // Set click listeners
        newQuoteButton.setOnClickListener(v -> showRandomQuote());
        saveQuoteButton.setOnClickListener(v -> saveFavoriteQuote());
        shareQuoteButton.setOnClickListener(v -> shareQuote());
        viewFavoritesButton.setOnClickListener(v -> viewFavorites());
    }

    // Populate the list with custom quotes
    private void populateQuotes() {
        quotes.add("The only way to do great work is to love what you do.");
        authors.add("Steve Jobs");

        quotes.add("Life is 10% what happens to us and 90% how we react to it.");
        authors.add("Charles R. Swindoll");

        quotes.add("The purpose of our lives is to be happy.");
        authors.add("Dalai Lama");

        quotes.add("Success is not final, failure is not fatal: It is the courage to continue that counts.");
        authors.add("Winston Churchill");

        quotes.add("You only live once, but if you do it right, once is enough.");
        authors.add("Mae West");

        // Add more quotes as needed
    }

    // Display a random quote
    private void showRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.size());
        String quote = quotes.get(index);
        String author = authors.get(index);
        quoteTextView.setText("\"" + quote + "\"");
        authorTextView.setText("- " + author);
    }

    // Save the current quote as a favorite
    private void saveFavoriteQuote() {
        String currentQuote = quoteTextView.getText().toString();
        String currentAuthor = authorTextView.getText().toString();

        // Save quote and author in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("favorite_quote", currentQuote);
        editor.putString("favorite_author", currentAuthor);
        editor.apply();

        Toast.makeText(this, "Quote saved as favorite!", Toast.LENGTH_SHORT).show();
    }

    // Share the current quote via social media or other apps
    private void shareQuote() {
        String quote = quoteTextView.getText().toString();

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, quote);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "Share Quote"));
    }

    // View saved favorite quotes
    private void viewFavorites() {
        Intent intent = new Intent(MainActivity.this, ViewFavoritesActivity.class);
        startActivity(intent);
    }
}
