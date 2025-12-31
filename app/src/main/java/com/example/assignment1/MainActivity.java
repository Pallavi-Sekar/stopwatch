package com.example.assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * MainActivity class for the Android Wear Stopwatch Application (2-Button Design)
 * 
 * This class implements a stopwatch functionality with only 2 buttons:
 * - Start/Stop toggle button (changes functionality based on state)
 * - Reset button
 * 
 * This implementation fulfills the bonus requirement for using only 2 buttons
 * while maintaining all the core functionality.
 * 
 * Features:
 * - Start/Stop/Reset stopwatch functionality using only 2 buttons
 * - Proper button state management and visual feedback
 * - Accurate time tracking using SystemClock.elapsedRealtime()
 * - UI optimized for Android Wear devices
 * 
 * @author Pallavi
 * @version 1.0
 * @since Android API 30 (Android 11)
 */
public class MainActivity extends Activity implements View.OnClickListener {

    // UI Components
    private TextView timerDisplay;    // Title display
    private TextView timeDisplay;     // Time display showing elapsed time
    private Button startStopButton;   // Toggle button for start/stop functionality
    private Button resetButton;       // Reset stopwatch button
    
    // Timer related variables
    private Handler handler;          // Handler for UI updates
    private long startTime = 0;       // Time when stopwatch was started
    private long elapsedTime = 0;     // Total elapsed time
    private boolean isRunning = false; // Current running state

    /**
     * Called when the activity is first created.
     * Initializes the UI components and sets up event listeners.
     * 
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize UI components
        initializeViews();
        
        // Initialize handler for UI updates
        handler = new Handler(Looper.getMainLooper());
        
        // Set up button click listeners using the implemented OnClickListener interface
        setupButtonListeners();
        
        // Initialize the display with default values
        updateDisplay();
        
        // Set initial button states
        updateButtonStates();
    }
    
    /**
     * Initialize all UI views and perform null checks for safety
     */
    private void initializeViews() {
        timerDisplay = findViewById(R.id.timerDisplay);
        timeDisplay = findViewById(R.id.timeDisplay);
        startStopButton = findViewById(R.id.startStopButton);
        resetButton = findViewById(R.id.resetButton);
        
        // Validate that all views were found
        if (timerDisplay == null) {
            android.util.Log.e("MainActivity", "timerDisplay not found!");
            return;
        }
        if (timeDisplay == null) {
            android.util.Log.e("MainActivity", "timeDisplay not found!");
            return;
        }
        if (startStopButton == null) {
            android.util.Log.e("MainActivity", "startStopButton not found!");
            return;
        }
        if (resetButton == null) {
            android.util.Log.e("MainActivity", "resetButton not found!");
            return;
        }
    }
    
    /**
     * Set up click listeners for all buttons using the implemented OnClickListener interface
     */
    private void setupButtonListeners() {
        startStopButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }
    
    /**
     * Implementation of OnClickListener interface for handling button clicks
     * 
     * @param v The view that was clicked
     */
    @Override
    public void onClick(View v) {
        // Handle button clicks based on the view ID
        if (v.getId() == R.id.startStopButton) {
            toggleStartStop();
        } else if (v.getId() == R.id.resetButton) {
            resetStopwatch();
        }
    }
    
    /**
     * Toggle between start and stop functionality
     * This method handles both start and stop operations based on current state
     */
    private void toggleStartStop() {
        if (isRunning) {
            // Currently running, so stop the stopwatch
            stopStopwatch();
        } else {
            // Currently stopped, so start the stopwatch
            startStopwatch();
        }
    }
    
    /**
     * Start the stopwatch timer
     * Only starts if the stopwatch is not already running
     */
    private void startStopwatch() {
        if (!isRunning) {
            // Calculate start time accounting for previously elapsed time
            startTime = SystemClock.elapsedRealtime() - elapsedTime;
            
            // Start the timer update loop
            handler.post(updateTimer);
            
            // Update running state
            isRunning = true;
            
            // Update button states to reflect new state
            updateButtonStates();
        }
    }
    
    /**
     * Stop the stopwatch timer
     * Only stops if the stopwatch is currently running
     */
    private void stopStopwatch() {
        if (isRunning) {
            // Remove any pending timer updates
            handler.removeCallbacks(updateTimer);
            
            // Update running state
            isRunning = false;
            
            // Update button states to reflect new state
            updateButtonStates();
        }
    }
    
    /**
     * Reset the stopwatch timer to zero
     * Stops the timer if running and resets all values
     */
    private void resetStopwatch() {
        // Stop the timer if it's running
        handler.removeCallbacks(updateTimer);
        
        // Reset all timer values
        isRunning = false;
        elapsedTime = 0;
        startTime = 0;
        
        // Update the display
        updateDisplay();
        
        // Update button states to reflect new state
        updateButtonStates();
    }
    
    /**
     * Runnable that updates the timer display
     * Calculates elapsed time using SystemClock.elapsedRealtime() for accuracy
     */
    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            // Calculate elapsed time using SystemClock.elapsedRealtime()
            // This ensures accuracy even if the app is paused/resumed
            elapsedTime = SystemClock.elapsedRealtime() - startTime;
            
            // Update the display
            updateDisplay();
            
            // Schedule the next update (every 10ms for smooth display)
            handler.postDelayed(this, 10);
        }
    };
    
    /**
     * Update the time display with the current elapsed time
     * Formats time as MM:SS.CC (minutes:seconds.centiseconds)
     */
    private void updateDisplay() {
        // Calculate time components
        int minutes = (int) (elapsedTime / 60000);
        int seconds = (int) (elapsedTime % 60000) / 1000;
        int centiseconds = (int) (elapsedTime % 1000) / 10;
        
        // Format time string
        String time = String.format("%02d:%02d.%02d", minutes, seconds, centiseconds);
        
        // Update the display if the view exists
        if (timeDisplay != null) {
            timeDisplay.setText(time);
        }
    }
    
    /**
     * Update button states based on the current stopwatch state
     * Implements the requirement for proper button enable/disable functionality
     * and updates button text and colors accordingly for the 2-button design
     */
    private void updateButtonStates() {
        if (isRunning) {
            // When running: change start/stop button to "STOP" and disable reset
            startStopButton.setText(getString(R.string.stop_button));
            startStopButton.setEnabled(true);
            startStopButton.setBackgroundColor(getResources().getColor(R.color.button_stop_red));
            
            resetButton.setEnabled(false);
            resetButton.setBackgroundColor(getResources().getColor(R.color.button_disabled_gray));
        } else {
            // When stopped: change start/stop button to "START" and enable reset
            startStopButton.setText(getString(R.string.start_button));
            startStopButton.setEnabled(true);
            startStopButton.setBackgroundColor(getResources().getColor(R.color.button_start_green));
            
            resetButton.setEnabled(true);
            resetButton.setBackgroundColor(getResources().getColor(R.color.button_reset_orange));
        }
    }
    
    /**
     * Called when the activity is paused
     * The stopwatch continues running in the background using SystemClock.elapsedRealtime()
     */
    @Override
    protected void onPause() {
        super.onPause();
        // Note: We don't stop the timer here as per requirements
        // The timer should continue running even when the user leaves the app
    }
    
    /**
     * Called when the activity is resumed
     * The stopwatch display will be updated with the correct elapsed time
     */
    @Override
    protected void onResume() {
        super.onResume();
        // Update the display to show current elapsed time
        updateDisplay();
    }
}