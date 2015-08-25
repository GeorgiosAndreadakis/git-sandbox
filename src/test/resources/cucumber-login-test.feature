Feature: Login

  Scenario: Georgios signs in
    Given User enters "georgios" as username
    When he also enters "georgios" as password
    Then he has successfully logged in