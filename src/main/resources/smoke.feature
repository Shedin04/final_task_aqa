Feature: Smoke
  As a user
  I would like to check that the acute functionalities of program is working fine
  I can do this by performing testing of the software product

  Scenario Outline: User entering the site can select the required category of goods and check count of found styles
    Given User opens '<homePage>' page
    And User checks central buttons
    And User checks image on homepage
    When User clicks type of shop '<button>' button
    And Page with title '<title>' is displayed
    And User checks goods features page
    And User clicks feature '<feature>' button
    And Page with title '<featureTitle>' is displayed
    Then count of styles is '<countOfStyles>'

    Examples:
      | homePage              | button     | title | feature                  | featureTitle | countOfStyles |
      | https://www.asos.com/ | SHOP MEN   | Men   | SMARTY PANTS             | Men          | 2,045         |
      | https://www.asos.com/ | SHOP WOMEN | Women | TEAM TOPSHOP             | Topshop      | 3,430         |
      | https://www.asos.com/ | SHOP MEN   | Men   | ASOS X POLO RALPH LAUREN | Ralph        | 610           |
      | https://www.asos.com/ | SHOP WOMEN | Women | THE NORTH FACE           | The          | 648           |

  Scenario Outline: User can find and open required pages in the header, filter goods presented there
    Given User opens '<homePage>' page
    And User checks header buttons
    And User clicks header '<button>' button
    And Page with title '<title>' is displayed
    And User checks logo
    And User clicks logo
    And Page with title '<title>' is displayed
    And User hover mouse over '<mainCategory>' category
    And User clicks element '<element>' in header
    And User checks '<pageName>' name of page
    And User checks filters
    When User clicks '<filter>' filter
    And User checks filter sections
    And User selects filter section '<section>'
    Then User checks that name of products contains '<productName>'

    Examples:
      | homePage              | button  | title | mainCategory | element          | pageName                    | filter | section | productName |
      | https://www.asos.com/ | MEN     | Men   | Sale         | SALE Tracksuits  | Sale: Tracksuits & Joggers  | Brand  | Fila    | Fila        |
      | https://www.asos.com/ | WOMEN   | Women | Outlet       | Maternity        | Outlet: Maternity           | Size   | UK 10   | Jaded       |

  Scenario Outline: User can find any product through search
    Given User opens '<homePage>' page
    And User checks search box
    When User enters request '<request>'
    And User clicks submit search button
    Then User checks that search result '<result>'
    And count of styles is '<foundStyles>'

    Examples:
      | homePage              | request               | result                      | foundStyles |
      | https://www.asos.com/ | Under Armour          | Under Armour                | 957         |
      | https://www.asos.com/ | Paris Saint Germain   | Paris Saint Germain         | 16          |
      | https://www.asos.com/ | 4qrqwrw45151          | NOTHING MATCHES YOUR SEARCH | 0           |
      | https://www.asos.com/ | New Balance           | New Balance                 | 712         |

  Scenario Outline: User entering request with an error receives required goods
    Given User opens '<homePage>' page
    And User checks search box
    When User enters request '<request>'
    And User clicks submit search button
    Then User checks that proposed request correction is '<suggestCorrection>'
    And User checks that name of products contains '<productName>'

    Examples:
      | homePage              | request             | suggestCorrection   | productName |
      | https://www.asos.com/ | Manckester          | manchester          | Puma        |
      | https://www.asos.com/ | lil & scott         | lyle scott          | Lyle        |

  Scenario Outline: User can switch location anywhere
                    As result, region and currency should be changed
    Given User opens '<homePage>' page
    And User checks buttons for changing location
    And User checks central buttons
    And User clicks type of shop '<button>' button
    When User clicks <buttonPosition> changing location button
    And User selects location '<location>'
    And User selects currency '<currency>'
    And User clicks save location button

    Examples:
      | homePage              | button     | buttonPosition | location | currency |
      | https://www.asos.com/ | SHOP MEN   | 0              | Poland   | EUR      |
      | https://www.asos.com/ | SHOP WOMEN | 1              | Georgia  | GBP      |

  Scenario Outline: User can join and login on site
    Given User opens '<homePage>' page
    And User checks profile button
    And User opens '<linkName>' link in profile dropdown
    And Page with title 'ASOS' is displayed
    And User checks email field
    And User checks password field
    And User enters email '<email>'
    And User enters password '<password>'
    When User clicks submit button
    Then Sign In is '<status>'
    And Page with title '<titleAfter>' is displayed

    Examples:
      | homePage              | linkName   | email                 | password   | status    | titleAfter |
      | https://www.asos.com/ | Sign In    | dmytro.shedin@nure.ua | qWeRtY0987 | success   | ASOS       |
      | https://www.asos.com/ | Sign In    | dmytro.shedin@nure.ua | 11eRtY0987 | fail      | ASOS       |
      | https://www.asos.com/ | My Orders  | dmytro.shedin@nure.ua | qWeRtY0987 | success   | My         |
      | https://www.asos.com/ | My Orders  | usermail@i.ua         | qWeRtY0987 | fail      | ASOS       |

  Scenario Outline: User can reset password
    Given User opens '<homePage>' page
    And User checks profile button
    And User opens '<linkName>' link in profile dropdown
    And User checks reset password link
    And User clicks reset password link
    And User checks reset password button
    And User enters email '<email>'
    When User clicks reset password button
    Then Message 'Reset password link sent' is shown

    Examples:
      | homePage              | linkName   | email                      |
      | https://www.asos.com/ | Sign In    | dmytro.shedin@nure.ua      |
      | https://www.asos.com/ | My Orders  | sfasfsafa@mail.com         |

  Scenario Outline: User can add product in wishlist
    Given User opens '<homePage>' page
    And User clicks header '<button>' button
    And User hover mouse over '<mainCategory>' category
    And User clicks element '<element>' in header
    And User selects product with '<productName>' name
    And User checks that product hero equals '<productName>' product name
    And User checks that wishlist button is displayed
    When User clicks wishlist button
    And User clicks wishlistHeaderButton
    Then User checks that product name in wishlist equals '<productName>'

    Examples:
      | homePage              | button | mainCategory | element   | productName                            |
      | https://www.asos.com/ | MEN    | Accessories  | Casio     | G Shock unisex smart watch in yellow   |
      | https://www.asos.com/ | WOMEN  | Face + Body  | Benefit   | Benefit Goof Proof Brow Pencil         |

  Scenario Outline: User can add product to bag
    Given User opens '<homePage>' page
    And User enters request '<request>'
    And User clicks submit search button
    And User selects product with '<productName>' name
    And User checks add to bag button
    And User checks that colour select field is '<colourStatus>'
    And User checks that size select field is '<sizeStatus>'
    And User selects product '<size>' size
    When User clicks add to bag button
    And User opens bag page
    Then Users checks that bag item name equals '<productName>'

    Examples:
      | homePage              | request     | productName                                             | colourStatus   | sizeStatus   | size        |
      | https://www.asos.com/ | Mancester   | Puma Football Manchester City 21/22 Home shirt in blue  | unavailable    | available    | EU 48 - 50  |
      | https://www.asos.com/ | New Balance | New Balance 574 wedge trainers in zebra print           | unavailable    | available    | EU 37.5     |