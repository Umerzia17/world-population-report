# Use Case Descriptions

## Use Case 1 – Generate Country Report
| Field | Description |
|-------|-------------|
| **Use Case Name** | Generate Country Report |
| **Primary Actor** | User |
| **Description** | Displays all countries sorted by population in descending order. |
| **Precondition** | Database connection is established. |
| **Main Flow** | 1. User selects Country Report.<br>2. System retrieves country data.<br>3. Countries are sorted by population.<br>4. Report is displayed. |
| **Postcondition** | Country report is displayed successfully. |

---

## Use Case 2 – Generate City Report
| Field | Description |
|-------|-------------|
| **Use Case Name** | Generate City Report |
| **Primary Actor** | User |
| **Description** | Displays all cities sorted by population. |
| **Precondition** | Database connection is established. |
| **Main Flow** | 1. User selects City Report.<br>2. System retrieves city data.<br>3. Cities are sorted by population.<br>4. Report is displayed. |
| **Postcondition** | City report is displayed successfully. |

---

## Use Case 3 – Generate Capital City Report
| Field | Description |
|-------|-------------|
| **Use Case Name** | Generate Capital City Report |
| **Primary Actor** | User |
| **Description** | Displays all capital cities sorted by population. |
| **Precondition** | Database connection is established. |
| **Main Flow** | 1. User selects Capital City Report.<br>2. System retrieves capital city data.<br>3. Capital cities are sorted by population.<br>4. Report is displayed. |
| **Postcondition** | Capital city report is displayed successfully. |

---

## Use Case 4 – Generate Top N Cities Report
| Field | Description |
|-------|-------------|
| **Use Case Name** | Generate Top N Cities Report |
| **Primary Actor** | User |
| **Description** | Displays the top N populated cities in the world. |
| **Precondition** | Database connection is established and the user enters a valid value for N. |
| **Main Flow** | 1. User enters N.<br>2. System retrieves the top N cities.<br>3. Report is displayed. |
| **Postcondition** | Top N cities report is displayed successfully. |

---

## Use Case 5 – Generate Population Report
| Field | Description |
|-------|-------------|
| **Use Case Name** | Generate Population Report |
| **Primary Actor** | User |
| **Description** | Displays the population of people, people living in cities, and people not living in cities for each country. |
| **Precondition** | Database connection is established. |
| **Main Flow** | 1. User selects Population Report.<br>2. System retrieves population data.<br>3. Report is displayed. |
| **Postcondition** | Population report is displayed successfully. |

---

## Use Case 6 – View Population Statistics
| Field | Description |
|-------|-------------|
| **Use Case Name** | View Population Statistics |
| **Primary Actor** | User |
| **Description** | Displays the population of the world, a continent, a region, a country, a district, or a city. |
| **Precondition** | Database connection is established. |
| **Main Flow** | 1. User requests a population value.<br>2. System retrieves the requested data.<br>3. Population is displayed. |
| **Postcondition** | Requested population statistic is displayed successfully. |

---

## Use Case 7 – Generate Language Report
| Field | Description |
|-------|-------------|
| **Use Case Name** | Generate Language Report |
| **Primary Actor** | User |
| **Description** | Displays the number of speakers of Chinese, English, and Spanish along with their percentage of the world's population. |
| **Precondition** | Database connection is established. |
| **Main Flow** | 1. User selects Language Report.<br>2. System retrieves language statistics.<br>3. Report is displayed in descending order of speakers. |
| **Postcondition** | Language report is displayed successfully. |