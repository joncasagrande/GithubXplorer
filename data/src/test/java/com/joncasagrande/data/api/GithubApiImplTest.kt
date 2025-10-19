package com.joncasagrande.data.api

import com.joncasagrande.data.model.Items
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
           val result =  githubApi.fetchRepos("")
            assertEquals(true, (result as NetworkResult.Success<List<Items>>).body.isNotEmpty())
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
            val result =  githubApi.fetchRepos("")
            assertEquals("Repo not found.", (result as NetworkResult.Error<List<Items>>).error.message)
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
            val result =  githubApi.fetchRepos("")
            assertEquals("Server Disruption! We are on fixing it.", (result as NetworkResult.Error<List<Items>>).error.message)
        }
    }


    val content = "[\n" +
            "  {\n" +
            "    \"id\": 113182661,\n" +
            "    \"node_id\": \"MDEwOlJlcG9zaXRvcnkxMTMxODI2NjE=\",\n" +
            "    \"name\": \"AdminLTE\",\n" +
            "    \"full_name\": \"joncasagrande/AdminLTE\",\n" +
            "    \"private\": false,\n" +
            "    \"owner\": {\n" +
            "      \"login\": \"joncasagrande\",\n" +
            "      \"id\": 802545,\n" +
            "      \"node_id\": \"MDQ6VXNlcjgwMjU0NQ==\",\n" +
            "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/802545?v=4\",\n" +
            "      \"gravatar_id\": \"\",\n" +
            "      \"url\": \"https://api.github.com/users/joncasagrande\",\n" +
            "      \"html_url\": \"https://github.com/joncasagrande\",\n" +
            "      \"followers_url\": \"https://api.github.com/users/joncasagrande/followers\",\n" +
            "      \"following_url\": \"https://api.github.com/users/joncasagrande/following{/other_user}\",\n" +
            "      \"gists_url\": \"https://api.github.com/users/joncasagrande/gists{/gist_id}\",\n" +
            "      \"starred_url\": \"https://api.github.com/users/joncasagrande/starred{/owner}{/repo}\",\n" +
            "      \"subscriptions_url\": \"https://api.github.com/users/joncasagrande/subscriptions\",\n" +
            "      \"organizations_url\": \"https://api.github.com/users/joncasagrande/orgs\",\n" +
            "      \"repos_url\": \"https://api.github.com/users/joncasagrande/repos\",\n" +
            "      \"events_url\": \"https://api.github.com/users/joncasagrande/events{/privacy}\",\n" +
            "      \"received_events_url\": \"https://api.github.com/users/joncasagrande/received_events\",\n" +
            "      \"type\": \"User\",\n" +
            "      \"user_view_type\": \"public\",\n" +
            "      \"site_admin\": false\n" +
            "    },\n" +
            "    \"html_url\": \"https://github.com/joncasagrande/AdminLTE\",\n" +
            "    \"description\": \"AdminLTE - Free Premium Admin control Panel Theme Based On Bootstrap 3.x\",\n" +
            "    \"fork\": true,\n" +
            "    \"url\": \"https://api.github.com/repos/joncasagrande/AdminLTE\",\n" +
            "    \"forks_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/forks\",\n" +
            "    \"keys_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/keys{/key_id}\",\n" +
            "    \"collaborators_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/collaborators{/collaborator}\",\n" +
            "    \"teams_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/teams\",\n" +
            "    \"hooks_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/hooks\",\n" +
            "    \"issue_events_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/issues/events{/number}\",\n" +
            "    \"events_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/events\",\n" +
            "    \"assignees_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/assignees{/user}\",\n" +
            "    \"branches_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/branches{/branch}\",\n" +
            "    \"tags_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/tags\",\n" +
            "    \"blobs_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/git/blobs{/sha}\",\n" +
            "    \"git_tags_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/git/tags{/sha}\",\n" +
            "    \"git_refs_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/git/refs{/sha}\",\n" +
            "    \"trees_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/git/trees{/sha}\",\n" +
            "    \"statuses_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/statuses/{sha}\",\n" +
            "    \"languages_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/languages\",\n" +
            "    \"stargazers_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/stargazers\",\n" +
            "    \"contributors_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/contributors\",\n" +
            "    \"subscribers_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/subscribers\",\n" +
            "    \"subscription_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/subscription\",\n" +
            "    \"commits_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/commits{/sha}\",\n" +
            "    \"git_commits_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/git/commits{/sha}\",\n" +
            "    \"comments_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/comments{/number}\",\n" +
            "    \"issue_comment_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/issues/comments{/number}\",\n" +
            "    \"contents_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/contents/{+path}\",\n" +
            "    \"compare_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/compare/{base}...{head}\",\n" +
            "    \"merges_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/merges\",\n" +
            "    \"archive_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/{archive_format}{/ref}\",\n" +
            "    \"downloads_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/downloads\",\n" +
            "    \"issues_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/issues{/number}\",\n" +
            "    \"pulls_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/pulls{/number}\",\n" +
            "    \"milestones_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/milestones{/number}\",\n" +
            "    \"notifications_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/notifications{?since,all,participating}\",\n" +
            "    \"labels_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/labels{/name}\",\n" +
            "    \"releases_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/releases{/id}\",\n" +
            "    \"deployments_url\": \"https://api.github.com/repos/joncasagrande/AdminLTE/deployments\",\n" +
            "    \"created_at\": \"2017-12-05T13:00:38Z\",\n" +
            "    \"updated_at\": \"2019-03-20T15:03:33Z\",\n" +
            "    \"pushed_at\": \"2017-12-07T00:14:46Z\",\n" +
            "    \"git_url\": \"git://github.com/joncasagrande/AdminLTE.git\",\n" +
            "    \"ssh_url\": \"git@github.com:joncasagrande/AdminLTE.git\",\n" +
            "    \"clone_url\": \"https://github.com/joncasagrande/AdminLTE.git\",\n" +
            "    \"svn_url\": \"https://github.com/joncasagrande/AdminLTE\",\n" +
            "    \"homepage\": \"https://adminlte.io\",\n" +
            "    \"size\": 49552,\n" +
            "    \"stargazers_count\": 0,\n" +
            "    \"watchers_count\": 0,\n" +
            "    \"language\": \"HTML\",\n" +
            "    \"has_issues\": false,\n" +
            "    \"has_projects\": true,\n" +
            "    \"has_downloads\": true,\n" +
            "    \"has_wiki\": true,\n" +
            "    \"has_pages\": false,\n" +
            "    \"has_discussions\": false,\n" +
            "    \"forks_count\": 0,\n" +
            "    \"mirror_url\": null,\n" +
            "    \"archived\": false,\n" +
            "    \"disabled\": false,\n" +
            "    \"open_issues_count\": 0,\n" +
            "    \"license\": {\n" +
            "      \"key\": \"mit\",\n" +
            "      \"name\": \"MIT License\",\n" +
            "      \"spdx_id\": \"MIT\",\n" +
            "      \"url\": \"https://api.github.com/licenses/mit\",\n" +
            "      \"node_id\": \"MDc6TGljZW5zZTEz\"\n" +
            "    },\n" +
            "    \"allow_forking\": true,\n" +
            "    \"is_template\": false,\n" +
            "    \"web_commit_signoff_required\": false,\n" +
            "    \"topics\": [\n" +
            "\n" +
            "    ],\n" +
            "    \"visibility\": \"public\",\n" +
            "    \"forks\": 0,\n" +
            "    \"open_issues\": 0,\n" +
            "    \"watchers\": 0,\n" +
            "    \"default_branch\": \"master\",\n" +
            "    \"permissions\": {\n" +
            "      \"admin\": true,\n" +
            "      \"maintain\": true,\n" +
            "      \"push\": true,\n" +
            "      \"triage\": true,\n" +
            "      \"pull\": true\n" +
            "    }\n" +
            "  }" +
            "]"
}