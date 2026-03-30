# Geldautomat (ATM Simulator)

A simple command-line ATM simulator written in Java.

## Features

- PIN login with max 3 attempts (account locks on too many failures)
- Check account balance
- Deposit money
- Withdraw money
- View full transaction history
- Clean input validation — no crashes on invalid input

## How to Run

1. Compile the source files:
   ```bash
   javac src/*.java -d out/
   ```

2. Run the application:
   ```bash
   java -cp out/ ATM
   ```

## Project Structure

```
Geldautomat/
├── src/
│   ├── ATM.java           # Main entry point, user interface
│   └── BankAccount.java   # Account data model and logic
├── README.md
├── CONTRIBUTORS.md
└── LICENSE
```

## Demo Account

The app starts with a demo account:
- **Name:** Simon
- **PIN:** 1234
- **Balance:** 500.00 EUR

## License

MIT License — see [LICENSE](LICENSE) for details.
