Feature: we can process user information

  Scenario: user makes call to GET /user/login
    When the user calls /user/login 1
    Then the user receive status code of 200