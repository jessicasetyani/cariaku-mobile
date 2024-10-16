package com.styletheory.cariaku.android.domain

object UserTable {
    const val NAME = "User"
    const val OBJECT_ID = "objectId"
    const val USERNAME = "username"
    const val EMAIL = "email"
    const val EMAIL_VERIFIED = "emailVerified"
    const val PASSWORD = "password"
    const val PREFERRED_LANGUAGE = "preferredLanguage"
    const val DATE_OF_BIRTH = "dateOfBirth"
    const val LAST_ACTIVE = "lastActive"
}

object AssistantTable {
    const val NAME = "Assistant"
    const val OBJECT_ID = "objectId"
    const val ASSISTANT_NAME = "name"
    const val ICON_URL = "icon_url"
    const val CUSTOM_PROMPT = "custom_prompt"
    const val TYPE = "type"
    const val IS_ACTIVE = "is_active"
    const val DESCRIPTION = "description"
    const val MODEL = "model"
    const val LANGUAGE = "language"
    const val VERSION = "version"
    const val CATEGORY = "category"
}

object UserAssistantTable {
    const val NAME = "UserAssistant"
    const val OBJECT_ID = "objectId"
    const val USER = "user"
    const val ASSISTANT = "assistant"
    const val LAST_USED = "last_used"
    const val IS_FAVORITE = "is_favorite"
    const val CUSTOM_PROMPT = "custom_prompt"
    const val CUSTOM_NAME = "custom_name"
    const val CUSTOM_ICON_URL = "custom_icon_url"
    const val USAGE_COUNT = "usage_count"
    const val LAST_FEEDBACK = "last_feedback"
}

object ChatTable {
    const val NAME = "Chat"
    const val OBJECT_ID = "objectId"
    const val TITLE = "title"
    const val USER = "user"
    const val ASSISTANT = "assistant"
    const val LAST_MESSAGE_AT = "last_message_at"
    const val IS_ACTIVE = "is_active"
}

object MessageTable {
    const val NAME = "Message"
    const val OBJECT_ID = "objectId"
    const val CONTENT = "content"
    const val SENDER_TYPE = "sender_type"
    const val CHAT = "chat"
    const val TIMESTAMP = "timestamp"
    const val IS_READ = "is_read"
    const val TOTAL_TOKENS = "total_tokens"
    const val ATTACHMENTS = "attachments"
    const val TEXT_CONTENT = "text_content"
    const val PROCESSED_CONTENT = "processed_content"
}

object AssistantCapabilityTable {
    const val NAME = "AssistantCapability"
    const val OBJECT_ID = "objectId"
    const val CAPABILITY_NAME = "name"
    const val DESCRIPTION = "description"
    const val ASSISTANT = "assistant"
    const val CATEGORY = "category"
}

object UserPreferenceTable {
    const val NAME = "UserPreference"
    const val OBJECT_ID = "objectId"
    const val KEY = "key"
    const val VALUE = "value"
    const val USER = "user"
}

object CategoryTable {
    const val NAME = "Category"
    const val OBJECT_ID = "objectId"
    const val CATEGORY_NAME = "name"
    const val DESCRIPTION = "description"
    const val ICON_URL = "icon_url"
    const val IS_ACTIVE = "is_active"
}

object KnowledgeBaseTable {
    const val NAME = "KnowledgeBase"
    const val OBJECT_ID = "objectId"
    const val KNOWLEDGE_NAME = "name"
    const val DESCRIPTION = "description"
    const val SOURCE_TYPE = "source_type"
    const val CONTENT_SUMMARY = "content_summary"
    const val LAST_UPDATED = "last_updated"
}

object UserActivityTable {
    const val NAME = "UserActivity"
    const val OBJECT_ID = "objectId"
    const val USER = "user"
    const val ACTIVITY_TYPE = "activity_type"
    const val TIMESTAMP = "timestamp"
    const val DETAILS = "details"
}

object FeedbackTable {
    const val NAME = "Feedback"
    const val OBJECT_ID = "objectId"
    const val USER = "user"
    const val ASSISTANT = "assistant"
    const val RATING = "rating"
    const val COMMENT = "comment"
    const val CREATED_AT = "created_at"
}
