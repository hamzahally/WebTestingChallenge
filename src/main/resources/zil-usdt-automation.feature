Feature: Navigate to trading pages on Crypto.com

  Scenario: Trade ZIL/USDT pair
    Given the browser is open
    And user navigates to the exchange market page
    When user clicks on ZIL/USDT pair
    Then the ZIL/USDT trade page will show

#  Scenario: User accidently clicks into wrong tab
#    Given the browser is open
#    And user navigates to the exchange market page
#    When user clicks on link other than ZIL/USDT pair
#    Then the ZIL/USDT trade page will not show