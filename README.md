# Subscription Manager

[![Maven Build and Test](https://github.com/KartoffelChipss/SubscriptionManager/actions/workflows/maven-build-and-test.yml/badge.svg)](https://github.com/KartoffelChipss/SubscriptionManager/actions/workflows/maven-build-and-test.yml)

A simple subscription manager application that allows you to get an overview of your subscriptions, how much you spend on them and when they are due. You can add every kind of subscription, organize them in categories and configure multiple profiles to keep track of subscriptions for different people.

## 🛠️ System Requirements

- **Java Version**: Java 21 or higher
- **Maven Version**: Apache Maven 3.9.9

## 📦 Installation

1. Clone the repository and navigate to the project directory:
   ```bash
   git clone https://github.com/KartoffelChipss/SubscriptionManager.git
   cd SubscriptionManager
   ```
2. Install dependencies and build the project:
   ```bash
   mvn clean install
   ```

## ⚡ Quick Start

To run the application, use one of the following commands:

1. Execute the application using the built JAR file:
   ```bash
   java -jar target/subscriptionmanager-1.0.0.jar
   ```
2. Alternatively, run it directly using Maven:
   ```bash
   mvn exec:java -Dexec.mainClass="org.strassburger.subscriptionmanager.Main"
   ```

### 🎯 Expected Behavior
- Upon starting the application, you should see the main menu.
- Use the options to navigate through the application.