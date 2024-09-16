package com.styletheory.cariaku.util

/**
 * Created by Jessica Setyani on 15-09-2024.
 */
object Constant {
    const val MODEL_AI_RELECTION = "mattshumer/reflection-70b:free"
    const val MODEL_AI_HERMES = "nousresearch/hermes-3-llama-3.1-405b:free"
    const val MODEL_AI_GEMINI = "google/gemini-flash-8b-1.5-exp"
    const val API_KEY_OPEN_ROUTE = "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"
    const val PROJECT_NAME_CARI_AKU = "CariAku"
    const val HEADER_X_TITLE = "X-Title"
    const val MAX_TOTAL_TOKEN = 4096
    const val BUFFER_TOKEN = 0.4 * MAX_TOTAL_TOKEN
    const val RENEW_TOKEN_QUOTA = MAX_TOTAL_TOKEN - BUFFER_TOKEN
    const val FORMAT_DATETIME_HH_MM_A = "hh:mm a"
    const val ROLE_SYSTEM = "system"
    const val ROLE_USER = "user"
    const val ROLE_ASSISTANT = "assistant"
    const val SYSTEM_PROMPT_INITIAL = "You are a knowledgeable and friendly assistant that provides clear and concise answers."
    const val SYSTEM_PROMPT_SUMMARY =
        "You are an AI model designed to summarize chat sequences between an AI and a user. Your task is to extract the key points of the conversation and present them in a concise and clear manner, including a title for the summary.\\n\\n- Focus on identifying the main topics discussed and the user's primary objectives.\\n- Provide a brief, relevant title that encapsulates the essence of the conversation.\\n- Format the output using XML tags, with the <title> tag for the summary title and the <summary> tag for the main content.\\n- Omit any redundant or unimportant details, ensuring the summary captures the essence of the exchange.\\n- Ensure the tone is neutral, objective, and professional.\\n- The final output should be written in a coherent and easy-to-read format, providing a high-level overview of the interaction.\\n- Maintain user privacy by not including sensitive or personal information in the summary.\\n\\nExample output format:\\n<title> [Insert relevant title here] </title>\\n<summary> [Insert concise summary here] </summary>"
    const val USER_PROMPT_SUMMARY = "Here is the chat sequence: "
}