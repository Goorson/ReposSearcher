GitHub repo project

This Spring Boot project is developed with Kotlin and integrates with GitHub APIs for listing all repos for a user. 
Before you can fully utilize this project, you must authenticate with GitHub using the GitHub CLI to avoid hitting the rate limit for unauthenticated API requests.

Prerequisites:
JDK 11 or newer,
Git,
Maven,
GitHub CLI

Setting Up the Project

Clone the repository:
git clone https://github.com/Goorson/ReposSearcher

Install the GitHub CLI:

The GitHub CLI (gh) is used for authenticating with GitHub from the terminal. Follow the official installation instructions to install gh on your system.
https://github.com/cli/cli#installation

Authenticate with GitHub:

After installing the GitHub CLI, authenticate with your GitHub credentials using the following commands:
gh auth login

Follow the prompts to complete the authentication process. This step is crucial for increasing your GitHub API rate limit and accessing private repositories or extended functionalities.

Running the Application:

Before running the application, ensure you have completed the authentication process with GitHub to avoid hitting the rate limit for unauthenticated API requests.

Build the project:

mvn clean install

Note: To omit rate limits, it is possible to use a Bearer token in API calls; nevertheless, it's not yet implemented. For more information on GitHub's rate limiting, please refer to the GitHub Developer documentation.


