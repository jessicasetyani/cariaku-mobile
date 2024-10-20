package com.styletheory.cariaku.android.domain

object UserTable {
    const val TABLE_NAME = "_User"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val USERNAME = "username"
    const val PASSWORD = "password"
    const val EMAIL = "email"
    const val EMAIL_VERIFIED = "emailVerified"
    const val AUTH_DATA = "authData"
    const val LAST_ACTIVE = "lastActive"
    const val USER_PROFILE = "userProfile"
}

object UserAssistantTable {
    const val TABLE_NAME = "UserAssistant"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
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
    const val TABLE_NAME = "Chat"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val TITLE = "title"
    const val USER = "user"
    const val ASSISTANT = "assistant"
    const val LAST_MESSAGE_AT = "last_message_at"
    const val IS_ACTIVE = "is_active"
}

object MessageTable {
    const val TABLE_NAME = "Message"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val CONTENT = "content"
    const val SENDER_TYPE = "sender_type"
    const val CHAT = "chat"
    const val TIMESTAMP = "timestamp"
    const val IS_READ = "is_read"
    const val TOTAL_TOKENS = "total_tokens"
    const val TEXT_CONTENT = "text_content"
    const val PROCESSED_CONTENT = "processed_content"
    const val ATTACHMENTS = "attachments"
}

object AssistantCapabilityTable {
    const val TABLE_NAME = "AssistantCapability"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val DESCRIPTION = "description"
    const val REQUIRED_PERMISSIONS = "required_permissions"
    const val ICON_URL = "icon_url"
    const val PINECONE_NAMESPACE = "pinecone_namespace"
}

object UserPreferenceTable {
    const val TABLE_NAME = "UserPreference"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val KEY = "key"
    const val VALUE = "value"
    const val USER = "user"
}

object CategoryTable {
    const val TABLE_NAME = "Category"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val NAME = "name"
    const val DESCRIPTION = "description"
    const val ICON_URL = "icon_url"
    const val IS_ACTIVE = "is_active"
}

object KnowledgeBaseTable {
    const val TABLE_NAME = "KnowledgeBase"
    const val OBJECT_ID = "objectId"
    const val KNOWLEDGE_NAME = "name"
    const val DESCRIPTION = "description"
    const val SOURCE_TYPE = "source_type"
    const val CONTENT_SUMMARY = "content_summary"
    const val LAST_UPDATED = "last_updated"
}

object FeedbackTable {
    const val TABLE_NAME = "Feedback"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val USER = "user"
    const val ASSISTANT = "assistant"
    const val CHAT = "chat"
    const val RATING = "rating"
    const val COMMENT = "comment"
    const val FEEDBACK_TYPE = "feedback_type"
}

object AssistantTable {
    const val TABLE_NAME = "Assistant"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val NAME = "name"
    const val IS_ACTIVE = "is_active"
    const val CUSTOM_PROMPT = "custom_prompt"
    const val MODEL = "model"
    const val LANGUAGE = "language"
    const val CATEGORY = "category"
    const val ASSISTANT_CAPABILITY = "assistant_capability"
    const val CURRENT_VERSION = "current_version"
}

object UserProfileTable {
    const val TABLE_NAME = "UserProfile"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val NAME = "name"
    const val DOB = "DoB"
    const val LANGUAGE = "language"
    const val GENDER = "gender"
    const val PROFILE_URL = "profile_url"
    const val JOB = "job"
    const val NATIONALITY = "nationality"
}

object UserActivityTable {
    const val TABLE_NAME = "UserActivity"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val USER = "user"
    const val ACTIVITY_TYPE = "activity_type"
    const val DETAILS = "details"
    const val RELATED_ASSISTANT = "related_assistant"
    const val RELATED_CHAT = "related_chat"
    const val DURATION = "duration"
}

object AttachmentTable {
    const val TABLE_NAME = "Attachment"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val MESSAGE = "message"
    const val FILE_NAME = "file_name"
    const val FILE_TYPE = "file_type"
    const val FILE_SIZE = "file_size"
    const val FILE_URL = "file_url"
}

object AssistantVersionTable {
    const val TABLE_NAME = "AssistantVersion"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val ASSISTANT = "assistant"
    const val VERSION_NUMBER = "version_number"
    const val CHANGES = "changes"
    const val IS_CURRENT = "is_current"
}

object SystemSettingsTable {
    const val TABLE_NAME = "SysSetting"
    const val OBJECT_ID = "objectId"
    const val CREATED_AT = "createdAt"
    const val UPDATED_AT = "updatedAt"
    const val ACL = "ACL"
    const val KEY = "key"
    const val VALUE = "value"
}