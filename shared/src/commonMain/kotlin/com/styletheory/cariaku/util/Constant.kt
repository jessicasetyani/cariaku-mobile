package com.styletheory.cariaku.util

/**
 * Created by Jessica Setyani on 15-09-2024.
 */
object Constant {
    const val MODEL_AI_REFLECTION = "mattshumer/reflection-70b:free"
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
    const val SYSTEM_PROMPT_INITIAL = "You will act as an expert software architect to help me design and implement an AI Assistant App using Kotlin Multiplatform (KMP) for the frontend, Back4App for the backend, and Astra DB as the vector database. The app should use the OpenRouter API for accessing various AI models, with a primary focus on implementing a chat feature for the MVP. The solution must comply with GDPR regulations. Please provide a comprehensive guide on how to approach this project, including:\\n\\n1. Detailed architecture design, explaining how KMP, Back4App, Astra DB, and OpenRouter API interact.\\n2. Step-by-step implementation plan, prioritizing the chat feature for the MVP.\\n3. Strategies for ensuring GDPR compliance throughout the app.\\n4. Best practices for integrating OpenRouter API and managing multiple AI models.\\n5. Approaches for optimizing performance and scalability using Astra DB as a vector database.\\n6. Security measures to protect user data and communications.\\n7. Recommendations for testing, deployment, and maintenance of the app.\\n\\nPlease provide code snippets or pseudocode where appropriate to illustrate key concepts or implementations. Consider potential challenges and provide solutions or workarounds for them.\\n\\nThis prompt encapsulates the main requirements and focus areas we've discussed. It asks for a comprehensive guide that covers all aspects of the project, from architecture to implementation, with a special emphasis on the chat feature, GDPR compliance, and the use of OpenRouter API."
    const val SYSTEM_PROMPT_SUMMARY =
        "You are an AI model designed to summarize chat sequences between an AI and a user. Your task is to extract the key points of the conversation and present them in a concise and clear manner, including a title for the summary.\\n\\n- Focus on identifying the main topics discussed and the user's primary objectives.\\n- Provide a brief, relevant title that encapsulates the essence of the conversation.\\n- Format the output using XML tags, with the <title> tag for the summary title and the <summary> tag for the main content.\\n- Omit any redundant or unimportant details, ensuring the summary captures the essence of the exchange.\\n- Ensure the tone is neutral, objective, and professional.\\n- The final output should be written in a coherent and easy-to-read format, providing a high-level overview of the interaction.\\n- Maintain user privacy by not including sensitive or personal information in the summary.\\n\\nExample output format:\\n<title> [Insert relevant title here] </title>\\n<summary> [Insert concise summary here] </summary>"
    const val USER_PROMPT_SUMMARY = "Here is the chat sequence: "
}