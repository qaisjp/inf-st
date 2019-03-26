# Informatics Software Testing 2018-19

This is what the Software Testing coursework _should have_ looked like.

There was some good structure to the coursework, but overall the quality of the content in task supplied was mediocre, and the source repository provided was abysmal.

Files in the original repo were named terribly, but can still be accessed in the "legacy" folder by their old filenames.

- **Issue date**: 25th February
- **Deadline**: 29th March, 6pm
- **Weighting**: 25% of the course
- **Groupwork**: groups of _2_, or individually

## Overview

You will be writing and evaluating tests for a "command line parsing" library we have written.

We have written two specifications: **`spec-a.pdf`** and **`spec-b.pdf`**. **`spec-a.pdf`** is for tasks 1 and 2, and **`spec-b.pdf`** is for task 3.

- **Task 1** covers _Functional Testing_, a black box testing technique.
- **Task 2** covers _Coverage Analysis_, which allows us to determine whether our tests cases actually cover our code.
- **Task 3** covers _Test-Driven Development_, which is an approach to software development where we first write tests, and then write code that satifies our tests.

| Task | Marks out of 100 |
|------|------------------|
| Task 1 | 25 marks       |
| Task 2 | 25 marks       |
| Task 3 | 50 marks       |

## Prerequisites

You are encouraged to write your coursework on DICE. If the code submitted does not compile on DICE, _it is likely to attract a score of 0_.

- You MUST use **[JUnit 4](https://junit.org/junit4/)** as your testing environment.
- You SHOULD use the [EclEmma](https://www.eclemma.org/) coverage analysis tool. (This is not required for Task 1.)
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

- **README.md**: an explanation of the coursework and the tasks you need to complete
- **parser.jar**: a parser library we have written (compiled).
- **spec-a.pdf**: the specification for our parser library, as used in tasks 1 and 2 only.
- **spec-b.pdf**: the specification for our parser library, as used in task 3 only.

In the `src` folder you will find:
- **st**: the source for our parser library (the source code of **parser.jar**)
- **Task1_Functional.jar**: skeleton code for Task 1
- **Task2_Coverage.jar**: skeleton code for Task 2
- **Task3_TDD_N.java**: skeleton code for Task 3
    - This `Task3` file is for both parts, so this file will need to be copied, with `_N` changed to `_1` or `_2` accordingly)


## How to set up your Eclipse workspace

These are instructions for setting up your Eclipse workspace in a way that doesn't mess up your repository.

1. Clone this repository somewhere
1. Create a workspace anywhere
1. Create a Java project called "inf-st" or whatever (do not link to any existing folder)
1. Right click `src` in the package explorer and select "Delete"
1. Right click `inf-st` (your Java project), select `Build Path -> Link Source...`
1. A "Source Folder" window should appear. Under "Linked folder location" hit "Browse..."
1. In the browse modal, select the `src` folder inside this repository. Hit OK.
1. The "Folder name:" field should say "src", and hit Finish
1. ???
1. PROFIT!

## **Task 1** - Functional Testing

Functional Testing is a black box testing technique. You should use `spec-a.pdf` as your specification for input and output.

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

## **Task 2** - Coverage Analysis

### 2.1

1. Take the JUnit tests you developed in _Task 1_ and measure the **branch coverage achieved**.
2. Take screenshots showing the coverage achieved by your tests, as reported by a coverage measurement tool like EclEmma.

### 2.2

1. Add more tests and attempt to improve your branch coverage as much as possible.
2. You may look at the source code and its structure to guide the development of your additional tests.
3. Please note that you may not be able to attain 100% coverage as there may be parts of the code that are unreachable in the provided implementation.
4. Measure the **branch coverage achieved** using the tests from _Task 1_ and your new tests from _Task 2.2_.
5. Take screenshots of these new tests, just like you did in _Task 2.1_.

**Submission**

You are expected to submit:

- Two screenshots showing the different levels of coverage you achieved (`coverage_1.jpg` and `coverage_2.jpg`).
- A Java file named `Task2_Coverage.java` containing **all the JUnit tests** used to achived maximum coverage.

As before, you should designate **one member of the group** to submit for your whole group.

Submit using the following command:
- `submit st cw1 Task2_Coverage.java coverage_1.jpg coverage2.jpg <...>`
- **Note that <...> signifies you submitting any other relevant files.**
