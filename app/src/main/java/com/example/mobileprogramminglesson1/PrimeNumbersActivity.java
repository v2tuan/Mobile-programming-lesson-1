package com.example.mobileprogramminglesson1;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PrimeNumbersActivity extends AppCompatActivity {
    private EditText etInput;
    private Button btnPrimes, btnSquares;
    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_numbers);

        // Ánh xạ các View
        etInput = findViewById(R.id.et_input);
        btnPrimes = findViewById(R.id.btn_primes);
        btnSquares = findViewById(R.id.btn_squares);
        tvOutput = findViewById(R.id.tv_output);

        // Xử lý in số nguyên tố
        btnPrimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> numbers = generateArray();
                ArrayList<Integer> primes = findPrimes(numbers);
                // Hiển thị kết quả
                tvOutput.setText("Vui lòng xem kết quả ở logcat");
                Log.d("Primes", "Số nguyên tố: " + primes.toString());
            }
        });

        // Xử lý in số chính phương
        btnSquares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lấy số phần tử mà người dùng nhập vào
                int numElements = getUserInput();

                if (numElements > 0) {
                    // Tạo mảng ngẫu nhiên với số phần tử như người dùng nhập
                    ArrayList<Integer> randomNumbers = generateRandomArray(numElements);

                    // Lọc các số chính phương trong mảng
                    ArrayList<Integer> squares = findSquares(randomNumbers);

                    // Hiển thị các số chính phương trong một Toast
                    Toast toast = Toast.makeText(getApplicationContext(), "Số chính phương: " + squares.toString(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 100);  // Gravity.TOP: đặt thông báo ở trên cùng
                    toast.show();

                    // Hiển thị số ngẫu nhiên trên màn hình
                    tvOutput.setText("Mảng ngẫu nhiên: " + randomNumbers.toString());
                } else {
                    Toast.makeText(PrimeNumbersActivity.this, "Vui lòng nhập số phần tử hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ArrayList<Integer> generateArray() {
        EditText editText = findViewById(R.id.et_input);
        String inputText = editText.getText().toString().trim();

        // Tách chuỗi thành mảng, sử dụng khoảng trắng làm dấu phân cách và chuyển thành ArrayList<Integer>
        ArrayList<Integer> numberList = new ArrayList<>();
        try {
            // Tách chuỗi, chuyển từng phần tử thành số và thêm vào ArrayList
            Arrays.stream(inputText.split("\\s+"))
                    .map(Integer::parseInt)  // Chuyển mỗi phần tử thành số nguyên
                    .forEach(numberList::add);  // Thêm vào ArrayList
            Log.d("Mảng số", numberList.toString());
        } catch (NumberFormatException e) {
            Log.e("Lỗi chuyển đổi", "Không thể chuyển chuỗi thành số: " + e.getMessage());
            Toast.makeText(PrimeNumbersActivity.this, "Lỗi chuyển đổi không thể chuyển chuỗi thành số: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return numberList;
    }

    // Hàm lấy số phần tử từ EditText
    private int getUserInput() {
        String inputText = etInput.getText().toString().trim();
        try {
            return Integer.parseInt(inputText); // Chuyển đổi thành số nguyên
        } catch (NumberFormatException e) {
            Log.e("Lỗi nhập liệu", "Không thể chuyển chuỗi thành số: " + e.getMessage());
            return 0; // Trả về 0 nếu không thể chuyển đổi
        }
    }

    // Hàm tạo mảng ngẫu nhiên
    private ArrayList<Integer> generateRandomArray(int numElements) {
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numElements; i++) {
            // Tạo số ngẫu nhiên trong phạm vi từ 1 đến 100
            randomNumbers.add(random.nextInt(100)+1);
        }
        return randomNumbers;
    }

    private ArrayList<Integer> findPrimes(ArrayList<Integer> numbers) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int num : numbers) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        return primes;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private ArrayList<Integer> findSquares(ArrayList<Integer> numbers) {
        ArrayList<Integer> squares = new ArrayList<>();
        for (int num : numbers) {
            if (isSquare(num)) {
                squares.add(num);
            }
        }
        return squares;
    }

    private boolean isSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
