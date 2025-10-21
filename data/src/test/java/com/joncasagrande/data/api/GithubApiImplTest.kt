package com.joncasagrande.data.api

import com.joncasagrande.data.model.Repos
import com.joncasagrande.data.utils.NetworkResult
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.gson.gson
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GithubApiImplTest {

    @MockK
    lateinit var client: HttpClient

    lateinit var githubApi: GithubApi

    @Test
    fun ClientSuccessTest() {
        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = content,
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            client = HttpClient(
                engine = mockEngine
            ) {
                install(ContentNegotiation) {
                    gson {
                        setPrettyPrinting()
                        disableHtmlEscaping()
                    }
                }
            }
            githubApi = GithubApiImpl(client)
           val result =  githubApi.fetchRepos()
            assertEquals(true, (result as NetworkResult.Success<List<Repos>>).body.isNotEmpty())
        }
    }

    @Test
    fun Client404Test() {
        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = content,
                    status = HttpStatusCode.NotFound,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            client = HttpClient(
                engine = mockEngine
            ) {
                install(ContentNegotiation) {
                    gson {
                        setPrettyPrinting()
                        disableHtmlEscaping()
                    }
                }
            }
            githubApi = GithubApiImpl(client)
            val result =  githubApi.fetchRepos()
            assertEquals("Repo not found.", (result as NetworkResult.Error<List<Repos>>).error.message)
        }
    }

    @Test
    fun Client500Test() {
        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = content,
                    status = HttpStatusCode.InternalServerError,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            client = HttpClient(
                engine = mockEngine
            ) {
                install(ContentNegotiation) {
                    gson {
                        setPrettyPrinting()
                        disableHtmlEscaping()
                    }
                }
            }
            githubApi = GithubApiImpl(client)
            val result =  githubApi.fetchRepos()
            assertEquals("Server Disruption! We are on fixing it.", (result as NetworkResult.Error<List<Repos>>).error.message)
        }
    }


    val content = "{\n" +
            "  \"total_count\": 3450939,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"id\": 887025,\n" +
            "      \"node_id\": \"MDEwOlJlcG9zaXRvcnk4ODcwMjU=\",\n" +
            "      \"name\": \"q\",\n" +
            "      \"full_name\": \"kriskowal/q\",\n" +
            "      \"private\": false,\n" +
            "      \"owner\": {\n" +
            "        \"login\": \"kriskowal\",\n" +
            "        \"id\": 60294,\n" +
            "        \"node_id\": \"MDQ6VXNlcjYwMjk0\",\n" +
            "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/60294?v=4\",\n" +
            "        \"gravatar_id\": \"\",\n" +
            "        \"url\": \"https://api.github.com/users/kriskowal\",\n" +
            "        \"html_url\": \"https://github.com/kriskowal\",\n" +
            "        \"followers_url\": \"https://api.github.com/users/kriskowal/followers\",\n" +
            "        \"following_url\": \"https://api.github.com/users/kriskowal/following{/other_user}\",\n" +
            "        \"gists_url\": \"https://api.github.com/users/kriskowal/gists{/gist_id}\",\n" +
            "        \"starred_url\": \"https://api.github.com/users/kriskowal/starred{/owner}{/repo}\",\n" +
            "        \"subscriptions_url\": \"https://api.github.com/users/kriskowal/subscriptions\",\n" +
            "        \"organizations_url\": \"https://api.github.com/users/kriskowal/orgs\",\n" +
            "        \"repos_url\": \"https://api.github.com/users/kriskowal/repos\",\n" +
            "        \"events_url\": \"https://api.github.com/users/kriskowal/events{/privacy}\",\n" +
            "        \"received_events_url\": \"https://api.github.com/users/kriskowal/received_events\",\n" +
            "        \"type\": \"User\",\n" +
            "        \"user_view_type\": \"public\",\n" +
            "        \"site_admin\": false\n" +
            "      },\n" +
            "      \"html_url\": \"https://github.com/kriskowal/q\",\n" +
            "      \"description\": \"A promise library for JavaScript\",\n" +
            "      \"fork\": false,\n" +
            "      \"url\": \"https://api.github.com/repos/kriskowal/q\",\n" +
            "      \"forks_url\": \"https://api.github.com/repos/kriskowal/q/forks\",\n" +
            "      \"keys_url\": \"https://api.github.com/repos/kriskowal/q/keys{/key_id}\",\n" +
            "      \"collaborators_url\": \"https://api.github.com/repos/kriskowal/q/collaborators{/collaborator}\",\n" +
            "      \"teams_url\": \"https://api.github.com/repos/kriskowal/q/teams\",\n" +
            "      \"hooks_url\": \"https://api.github.com/repos/kriskowal/q/hooks\",\n" +
            "      \"issue_events_url\": \"https://api.github.com/repos/kriskowal/q/issues/events{/number}\",\n" +
            "      \"events_url\": \"https://api.github.com/repos/kriskowal/q/events\",\n" +
            "      \"assignees_url\": \"https://api.github.com/repos/kriskowal/q/assignees{/user}\",\n" +
            "      \"branches_url\": \"https://api.github.com/repos/kriskowal/q/branches{/branch}\",\n" +
            "      \"tags_url\": \"https://api.github.com/repos/kriskowal/q/tags\",\n" +
            "      \"blobs_url\": \"https://api.github.com/repos/kriskowal/q/git/blobs{/sha}\",\n" +
            "      \"git_tags_url\": \"https://api.github.com/repos/kriskowal/q/git/tags{/sha}\",\n" +
            "      \"git_refs_url\": \"https://api.github.com/repos/kriskowal/q/git/refs{/sha}\",\n" +
            "      \"trees_url\": \"https://api.github.com/repos/kriskowal/q/git/trees{/sha}\",\n" +
            "      \"statuses_url\": \"https://api.github.com/repos/kriskowal/q/statuses/{sha}\",\n" +
            "      \"languages_url\": \"https://api.github.com/repos/kriskowal/q/languages\",\n" +
            "      \"stargazers_url\": \"https://api.github.com/repos/kriskowal/q/stargazers\",\n" +
            "      \"contributors_url\": \"https://api.github.com/repos/kriskowal/q/contributors\",\n" +
            "      \"subscribers_url\": \"https://api.github.com/repos/kriskowal/q/subscribers\",\n" +
            "      \"subscription_url\": \"https://api.github.com/repos/kriskowal/q/subscription\",\n" +
            "      \"commits_url\": \"https://api.github.com/repos/kriskowal/q/commits{/sha}\",\n" +
            "      \"git_commits_url\": \"https://api.github.com/repos/kriskowal/q/git/commits{/sha}\",\n" +
            "      \"comments_url\": \"https://api.github.com/repos/kriskowal/q/comments{/number}\",\n" +
            "      \"issue_comment_url\": \"https://api.github.com/repos/kriskowal/q/issues/comments{/number}\",\n" +
            "      \"contents_url\": \"https://api.github.com/repos/kriskowal/q/contents/{+path}\",\n" +
            "      \"compare_url\": \"https://api.github.com/repos/kriskowal/q/compare/{base}...{head}\",\n" +
            "      \"merges_url\": \"https://api.github.com/repos/kriskowal/q/merges\",\n" +
            "      \"archive_url\": \"https://api.github.com/repos/kriskowal/q/{archive_format}{/ref}\",\n" +
            "      \"downloads_url\": \"https://api.github.com/repos/kriskowal/q/downloads\",\n" +
            "      \"issues_url\": \"https://api.github.com/repos/kriskowal/q/issues{/number}\",\n" +
            "      \"pulls_url\": \"https://api.github.com/repos/kriskowal/q/pulls{/number}\",\n" +
            "      \"milestones_url\": \"https://api.github.com/repos/kriskowal/q/milestones{/number}\",\n" +
            "      \"notifications_url\": \"https://api.github.com/repos/kriskowal/q/notifications{?since,all,participating}\",\n" +
            "      \"labels_url\": \"https://api.github.com/repos/kriskowal/q/labels{/name}\",\n" +
            "      \"releases_url\": \"https://api.github.com/repos/kriskowal/q/releases{/id}\",\n" +
            "      \"deployments_url\": \"https://api.github.com/repos/kriskowal/q/deployments\",\n" +
            "      \"created_at\": \"2010-09-04T01:21:12Z\",\n" +
            "      \"updated_at\": \"2025-10-19T10:10:55Z\",\n" +
            "      \"pushed_at\": \"2023-11-08T10:50:34Z\",\n" +
            "      \"git_url\": \"git://github.com/kriskowal/q.git\",\n" +
            "      \"ssh_url\": \"git@github.com:kriskowal/q.git\",\n" +
            "      \"clone_url\": \"https://github.com/kriskowal/q.git\",\n" +
            "      \"svn_url\": \"https://github.com/kriskowal/q\",\n" +
            "      \"homepage\": \"\",\n" +
            "      \"size\": 1428,\n" +
            "      \"stargazers_count\": 15198,\n" +
            "      \"watchers_count\": 15198,\n" +
            "      \"language\": \"JavaScript\",\n" +
            "      \"has_issues\": true,\n" +
            "      \"has_projects\": true,\n" +
            "      \"has_downloads\": true,\n" +
            "      \"has_wiki\": true,\n" +
            "      \"has_pages\": true,\n" +
            "      \"has_discussions\": false,\n" +
            "      \"forks_count\": 1188,\n" +
            "      \"mirror_url\": null,\n" +
            "      \"archived\": true,\n" +
            "      \"disabled\": false,\n" +
            "      \"open_issues_count\": 115,\n" +
            "      \"license\": {\n" +
            "        \"key\": \"mit\",\n" +
            "        \"name\": \"MIT License\",\n" +
            "        \"spdx_id\": \"MIT\",\n" +
            "        \"url\": \"https://api.github.com/licenses/mit\",\n" +
            "        \"node_id\": \"MDc6TGljZW5zZTEz\"\n" +
            "      },\n" +
            "      \"allow_forking\": true,\n" +
            "      \"is_template\": false,\n" +
            "      \"web_commit_signoff_required\": false,\n" +
            "      \"topics\": [],\n" +
            "      \"visibility\": \"public\",\n" +
            "      \"forks\": 1188,\n" +
            "      \"open_issues\": 115,\n" +
            "      \"watchers\": 15198,\n" +
            "      \"default_branch\": \"master\",\n" +
            "      \"permissions\": {\n" +
            "        \"admin\": false,\n" +
            "        \"maintain\": false,\n" +
            "        \"push\": false,\n" +
            "        \"triage\": false,\n" +
            "        \"pull\": true\n" +
            "      },\n" +
            "      \"score\": 1\n" +
            "    }" +
            "]" +
            "}"
}