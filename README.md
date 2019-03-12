# Informatics Software Testing 2018-19

This is what the Software Testing coursework _should have_ looked like.

There was some good structure to the coursework, but overall the quality of the content in task supplied was mediocre, and the source repository provided was abysmal.

Files in the original repo were named terribly, but can still be accessed in the "legacy" folder by their old filenames.

## Deadline

- Issued: 25th February
- **Deadline: 29th March, 6pm**

## Overview

You will be writing and evaluating tests for a "command line parsing" library we have written.

We have written two specifications: **`spec-a.pdf`** and **`spec-b.pdf`**. **`spec-a.pdf`** is for tasks 1 and 2, and **`spec-b.pdf`** is for task 3.

- **Task 1** covers _Functional Testing_, a black box testing technique.
- **Task 2** covers _Coverage Analysis_, which allows us to determine whether our tests cases actually cover our code.
- **Task 3** covers _Test-Driven Development__, which is an approach to software development where we first write tests, and then write code that satifies our tests.

## Prerequisites

You are encouraged to write your coursework on DICE. If the code submitted does not compile on DICE, _it is likely to attract a score of 0_.

- You MUST use **[JUnit 4](https://junit.org/junit4/)** as your testing environment.
- You SHOULD use the [EclEmma](https://www.eclemma.org/) coverage analysis tool.
- You CAN use the Eclipse IDE.
    - **Why? Is this required?**
    
        No, this is not required, but:
        - Eclipse is already installed on DICE.
        - Eclipse has JUnit 4 already integrated.
        - On DICE, EclEmma is already installed.

If you do not know how to use JUnit, here are useful articles:
    - [Introduction to JUnit 4](https://www.ibm.com/developerworks/java/tutorials/j-junit4/) (free registration required)
    - [Using JUnit with Eclipse](https://www.vogella.com/tutorials/JUnit/article.html#eclipse-support-for-junit-4) (this article is written for JUnit 3)

## An overview of this repository

In the root of this repository, you will find the following files

- **task.pdf**: the three tasks required to complete this coursework.
- **parser.jar**: a parser library we have written (compiled).
- **spec-a.pdf**: the specification for our parser library, as used in tasks 1 and 2 only.
- **spec-b.pdf**: the specification for our parser library, as used in task 3 only.

In the `src` folder you will find:
- **st**: the source for our parser library (the source code of **parser.jar**)
- **Task1_Functional.jar**: skeleton code for Task 1
- **Task2_Coverage.jar**: skeleton code for Task 2
- **Task3_TDD_N.java**: skeleton code for Task 3
    - This `Task3` file is for both parts, so this file will need to be copied, with `_N` changed to `_1` or `_2` accordingly)


## **Task 1** - Functional Testing

Functional Testing is a black box testing technique. You should use 

You will be required to populate `Task1_Functional.java` with tests that verify the effect that certain input has on the output.

**For this task, make sure you only use the `parser.jar` JAR library to perform functional testing. Do not use the source.**

An example of how this might look, is this:

```java
// Imports have been omitted
public class Task1_Functional {
	private Parser parser;
	
	@Before
	public void setup() {
		parser = new Parser();
	}
	
	@Test
	public void example() {
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output=output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}
}

```

**Marking**

Your tests will be run against a collection of _variants_ of the specification. The performance of your tests against these variants will affect your grade.

**Submission**

You are expected to submit just one file that contains all your JUnit tests: `Task1_Functional.java`.

You should designate **one member of the group** to submit for your whole group.

Submit using the following command `submit st cw1 Task1_Functional.java`.
