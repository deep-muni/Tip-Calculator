package com.deepmuni.tipcalculator

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Method is invoked to set the default value of amount, tip and party size
        reset()

        // The on change listener will detect the change and invoke the method setTip()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                setTip()
                calculate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                setTip()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                setTip()
            }
        })

    }

    // This function will will set the tip text view with the progress on seek bar
    private fun setTip() {
        val progress = seekBar.progress.toString()

        if(seekBar.progress < 10){
            tipPercent.text = getString(R.string.addZero, progress)
        }else{
            tipPercent.text = progress
        }
    }

    // This function is invoked when any button is clicked in the activity
    override fun onClick(view: View?) {

        val whichButton = view as Button

        /*
            Haptic feedback, in this case vibration, is provided to user when
            they click on any button.

            Reference => https://developer.android.com/reference/android/view/HapticFeedbackConstants
        */
        whichButton.isHapticFeedbackEnabled = true
        whichButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING)

        // According to the text in the button it will invoke the methods

        when (whichButton.text) {
            getString(R.string.minus) -> {
                decrement()
            }
            getString(R.string.plus) -> {
                increment()
            }
            getString(R.string.reset) -> {
                reset()
            }
            getString(R.string.clear) -> {
                removeDigit()
            }
            else -> {
                appendDigit(whichButton)
            }
        }
        /*
            After user clicks the button, it will auto calculate the tip, total and share per head
            by invoking calculate() method
        */

        calculate()
    }

    /*
        In this method, it will fetch value of amount, tip percent
        and number of people and perform the operation.
        After this, it will update the value in respective text view
    */
    private fun calculate(){
        val amountVal = billAmount.text.toString().toDouble()
        val tipVal = tipPercent.text.toString().toDouble()
        val people = numberOfPeople.text.toString().toInt()

        val tipAmount = (amountVal * tipVal / 100)
        val totalAmount = (amountVal + tipAmount)
        val shareAmount = (totalAmount / people)

        tip.text = getString(R.string.addDollar, String.format("%.2f", tipAmount).toDouble().toString())
        total.text = getString(R.string.addDollar, String.format("%.2f", totalAmount).toDouble().toString())
        share.text = getString(R.string.addDollar, String.format("%.2f", shareAmount).toDouble().toString())
    }

    // This method is invoked when user presses "C" clear button
    private fun removeDigit() {
        val amountVal = billAmount.text.toString()
        val digitArray = amountVal.toCharArray()
        val lastDigit = (digitArray[digitArray.size-1]).toString().toInt()
        val secondLastDigit = (digitArray[digitArray.size-2]).toString().toInt()

        if(lastDigit.toString() == "0"){
            val value = amountVal.toDouble()/10.0
            billAmount.text = String.format("%.2f", value).toDouble().toString()
            if(secondLastDigit.toString() == "0"){
                billAmount.text =  getString(R.string.addZeroEnd, billAmount.text.toString())
            }
        }else{
            val value = (amountVal.toDouble() - lastDigit/100.0)/10
            billAmount.text = String.format("%.2f", value).toDouble().toString()
            if(secondLastDigit.toString() == "0"){
                billAmount.text =  getString(R.string.addZeroEnd, billAmount.text.toString())
            }
        }
    }

    // This method will append the number in the amount field
    private fun appendDigit(whichButton: Button) {
        val amountVal = billAmount.text.toString().toDouble()
        val buttonVal = (whichButton.text.toString().toDouble())/100.0
        val amountText : String

        amountText = when {
            whichButton.text.toString() == "00" -> {
                getString(R.string.addZeroEnd, String.format("%.2f", amountVal*100).toDouble().toString())
            }
            whichButton.text.toString() == "0" -> {
                getString(R.string.addZeroEnd, String.format("%.2f", amountVal*10).toDouble().toString())
            }
            else -> {
                String.format("%.2f", (amountVal*10 + buttonVal)).toDouble().toString()
            }
        }
        if(amountText.length > 9){
            Toast.makeText(applicationContext,"Entered amount is too large!!", Toast.LENGTH_SHORT).show()
        }else{
            billAmount.text = amountText
        }
    }

    // This method is used to reset the values to default
    private fun reset() {

        seekBar.progress = 15
        billAmount.text = getString(R.string.zeros)
        tipPercent.text = seekBar.progress.toString()
        numberOfPeople.text = getString(R.string.addZero, getString(R.string.one))

        tip.text = getString(R.string.addDollar, "0.0")
        total.text = getString(R.string.addDollar, "0.0")
        share.text = getString(R.string.addDollar, "0.0")
    }

    // This method will increment the value of number of people by 1
    private fun increment() {
        val incrementCount = numberOfPeople.text.toString().toInt() + 1
        if(incrementCount < 10){
            numberOfPeople.text = getString(R.string.addZero, incrementCount.toString())
        }else{
            numberOfPeople.text = incrementCount.toString()
        }
    }

    // This method will decrement the value of number of people by 1
    private fun decrement() {
        if(numberOfPeople.text.toString().toInt() > 1){             // This condition will not allow number of people to be 0
            val decrementCount = numberOfPeople.text.toString().toInt() - 1
            if(decrementCount < 10){
                numberOfPeople.text = getString(R.string.addZero, decrementCount.toString())
            }else{
                numberOfPeople.text = decrementCount.toString()
            }
        }else{
            numberOfPeople.text = getString(R.string.addZero, getString(R.string.one))
        }
    }

}
