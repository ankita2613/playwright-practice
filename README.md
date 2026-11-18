# Playwright Practice: Sauce Demo Test Automation

![Playwright tests](https://github.com/ankita2613/playwright-practice/actions/workflows/tests.yml/badge.svg)

UI tests for https://www.saucedemo.com written with Playwright for Java.
A matching Selenium version is in my `selenium-practice` repository, so the two tools can be compared.

## Tools
Java 25, Playwright for Java, TestNG, Maven, Page Object Model

## What is tested
- Login: valid user, wrong password, and 7 data-driven invalid cases
- Checkout: add two items, check the cart, complete the order

## How to run
- All tests: `mvn clean test`
- Smoke tests only: `mvn clean test "-Dgroups=smoke"`

## Features
- Page Object Model
- No explicit waits (Playwright auto-waits)
- Smoke and regression groups
- Trace saved automatically when a test fails (open at trace.playwright.dev)

## Selenium vs Playwright: what I measured
| Measure | Selenium | Playwright |
| Time, full run | 39.57 s | 27.2 s (10 tests) |
| Time, smoke run | 29.63 s | 19.7 s (2 tests) |
| Wait code in page classes | Explicit waits needed | None |
| Failure evidence | Screenshot | Trace with steps, screenshots, network |

## What I learned
My first version launched a new browser for every test and took 45.2 s.
Launching the browser once per class, with a fresh context per test, cut the full run to 27.2 s.
