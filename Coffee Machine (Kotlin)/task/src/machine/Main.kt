package machine

import java.util.Scanner

var water = 400
var milk = 540
var beans = 120
var cups = 9
var money = 550

fun main() {

    val scanner = Scanner(System.`in`)
    val coffeeMachine = CoffeeMachine()

    while (true) {
        val command = scanner.next()
        coffeeMachine.input(command)
        if (coffeeMachine.state == MachineState.EXIT) break
    }

    /*val scanner = Scanner(System.`in`)

    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        when (scanner.next()) {
            "buy" -> buy(scanner)
            "fill" -> fill(scanner)
            "take" -> take()
            "remaining" -> printState()
            "exit" -> return
            else -> println("Unknown action. Please try again.")
        }
    }*/
}

fun printState() {
    println("$water ml of water")
    println("$milk ml of milk")
    println("$beans g of coffee beans")
    println("$cups disposable cups")
    println("$$money of money")
}

fun buy(scanner: Scanner) {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
    when (scanner.next()) {
        "1" -> makeCoffee(250, 0, 16, 4)
        "2" -> makeCoffee(350, 75, 20, 7)
        "3" -> makeCoffee(200, 100, 12, 6)
        else -> println("Unknown coffee type. Please try again.")
    }
}

fun fill(scanner: Scanner) {
    println("Write how many ml of water you want to add: ")
    water += scanner.nextInt()
    println("Write how many ml of milk you want to add: ")
    milk += scanner.nextInt()
    println("Write how many grams of coffee beans you want to add: ")
    beans += scanner.nextInt()
    println("Write how many disposable cups you want to add: ")
    cups += scanner.nextInt()
}

fun take() {
    println("I gave you \$$money")
    money = 0
}

fun makeCoffee(requiredWater: Int, requiredMilk: Int, requiredBeans: Int, price: Int) {
    when {
        water < requiredWater -> println("Sorry, not enough water!")
        milk < requiredMilk -> println("Sorry, not enough milk!")
        beans < requiredBeans -> println("Sorry, not enough coffee beans!")
        cups < 1 -> println("Sorry, not enough cups!")
        else -> {
            water -= requiredWater
            milk -= requiredMilk
            beans -= requiredBeans
            cups -= 1
            money += price
            println("I have enough resources, making you a coffee!")
        }
    }
}

enum class MachineState {
    CHOOSING_ACTION,
    CHOOSING_COFFEE,
    FILLING_WATER,
    FILLING_MILK,
    FILLING_BEANS,
    FILLING_CUPS,
    EXIT
}

class CoffeeMachine{
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550
    var state: MachineState = MachineState.CHOOSING_ACTION

    fun input(command: String) {
        when (state) {
            MachineState.CHOOSING_ACTION -> handleAction(command)
            MachineState.CHOOSING_COFFEE -> handleCoffeeChoice(command)
            MachineState.FILLING_WATER -> fillWater(command)
            MachineState.FILLING_MILK -> fillMilk(command)
            MachineState.FILLING_BEANS -> fillBeans(command)
            MachineState.FILLING_CUPS -> fillCups(command)
        }
    }

    private fun handleAction(action: String) {
        when (action) {
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                state = MachineState.CHOOSING_COFFEE
            }
            "fill" -> {
                println("Write how many ml of water you want to add:")
                state = MachineState.FILLING_WATER
            }
            "take" -> take()
            "remaining" -> printState()
            "exit" -> state = MachineState.EXIT
            else -> println("Unknown action. Please try again.")
        }
    }


    private fun handleCoffeeChoice(choice: String) {
        when (choice) {
            "1" -> makeCoffee(250, 0, 16, 4)
            "2" -> makeCoffee(350, 75, 20, 7)
            "3" -> makeCoffee(200, 100, 12, 6)
            "back" -> state = MachineState.CHOOSING_ACTION
            else -> println("Unknown coffee type. Please try again.")
        }
        if (state != MachineState.CHOOSING_ACTION) {
            println("Write action (buy, fill, take, remaining, exit):")
            state = MachineState.CHOOSING_ACTION
        }
    }

    private fun fillWater(amount: String) {
        water += amount.toInt()
        println("Write how many ml of milk you want to add:")
        state = MachineState.FILLING_MILK
    }

    private fun fillMilk(amount: String) {
        milk += amount.toInt()
        println("Write how many grams of coffee beans you want to add:")
        state = MachineState.FILLING_BEANS
    }

    private fun fillBeans(amount: String) {
        beans += amount.toInt()
        println("Write how many disposable cups you want to add:")
        state = MachineState.FILLING_CUPS
    }

    private fun fillCups(amount: String) {
        cups += amount.toInt()
        println("Write action (buy, fill, take, remaining, exit):")
        state = MachineState.CHOOSING_ACTION
    }

    private fun take() {
        println("I gave you \$$money")
        money = 0
        println("Write action (buy, fill, take, remaining, exit):")
        state = MachineState.CHOOSING_ACTION
    }

    private fun printState() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$beans g of coffee beans")
        println("$cups disposable cups")
        println("$$money of money")
        println("Write action (buy, fill, take, remaining, exit):")
    }

    private fun makeCoffee(requiredWater: Int, requiredMilk: Int, requiredBeans: Int, price: Int) {
        when {
            water < requiredWater -> println("Sorry, not enough water!")
            milk < requiredMilk -> println("Sorry, not enough milk!")
            beans < requiredBeans -> println("Sorry, not enough coffee beans!")
            cups < 1 -> println("Sorry, not enough cups!")
            else -> {
                water -= requiredWater
                milk -= requiredMilk
                beans -= requiredBeans
                cups -= 1
                money += price
                println("I have enough resources, making you a coffee!")
            }
        }
        println("Write action (buy, fill, take, remaining, exit):")
        state = MachineState.CHOOSING_ACTION
    }
}
