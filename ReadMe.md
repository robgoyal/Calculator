## Calculator 

### Purpose

A basic calculator app created in Java. 

### Algorithm

This app uses Djikstra's Two Stack algorithm. The caveat with this algorithm is that it requires brackets around each operation to evaluate it. For example, this algorithm wouldn't evaluate 2 + 2 - 5. However, it can evaluate a complex expression with an efficient method such as ((5 * 4) + ((5 / 2) + 4). 

The algorithm uses two stacks, one for operators and one for values. Each character is parsed, if the algorithm comes across a "(", it's ignored, if it comes across an operator, it's pushed onto the operators stack and if it comes across a number, its pushed onto the values stack. The beauty of this algorithm occurs when you parse a ")". Two values are popped from the values stack, an operator is popped from the operator stack and then the result of (value1 operator value2) is pushed back onto the values stack. 

### Procedure

This application uses the Swing GUI to display buttons and two text fields. One text field displays the current expression and the second text field displays the last result. There is a button included that allows the user to also include the previous result in the current expression. 

The equals button evalutes the current expression and displays the result. The only error checking that currently occurs is a divide by zero. This application also expects users to maintain brackets around the expressions they're looking to evaluate. In other words, it'll only follow BEDMAS if the user chooses to add brackets. I did make it so that the user is able to forgo one pair of brackets. So instead of ((5+4) * 3), it can be (5+4)*3 just in case the user forgets a pair of brackets.
