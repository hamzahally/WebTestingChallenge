Feature: Trade ZIL/USDT on Crypto.com

  Scenario: Trade ZIL/USDT pair
    Given the browser is Open
    And user navigates is at the exchange markets
    When user clicks on ZIL/USDT pair
    Then the ZIL/USDT trade page will show