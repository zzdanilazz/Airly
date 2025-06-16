package ru.health.featureachievement.api.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class ApproveParams(
    @StringRes val titleRes: Int? = null,
    @StringRes val descriptionRes: Int? = null,
    @StringRes val approveButtonTextRes: Int? = null,
    @StringRes val declineButtonTextRes: Int? = null,
    @StringRes val fieldTextPlaceholderRes: Int? = null,
    @DrawableRes val switchIconResList: List<Int>? = null,

    val title: String? = null,
    val description: String? = null,
    val approveButtonText: String? = null,
    val declineButtonText: String? = null,
    val fieldTextPlaceholder: String? = null,
    val fieldTrailingText: String? = null
) {

    class Builder {
        private var titleRes: Int? = null
        private var descriptionRes: Int? = null
        private var approveButtonTextRes: Int? = null
        private var declineButtonTextRes: Int? = null
        private var fieldTextPlaceholderRes: Int? = null
        private var switchIconResList: List<Int>? = null

        private var title: String? = null
        private var description: String? = null
        private var approveButtonText: String? = null
        private var declineButtonText: String? = null
        private var fieldTextPlaceholder: String? = null
        private var fieldTrailingText: String? = null

        fun setTitle(@StringRes title: Int?) = apply { this.titleRes = title }
        fun setTitle(title: String?) = apply { this.title = title }

        fun setDescription(@StringRes description: Int?) = apply { this.descriptionRes = description }
        fun setDescription(description: String?) = apply { this.description = description }

        fun setApproveButtonText(@StringRes text: Int?) = apply { this.approveButtonTextRes = text }
        fun setApproveButtonText(text: String?) = apply { this.approveButtonText = text }

        fun setDeclineButtonText(@StringRes text: Int?) = apply { this.declineButtonTextRes = text }
        fun setDeclineButtonText(text: String?) = apply { this.declineButtonText = text }

        fun setFieldTextPlaceholder(@StringRes text: Int?) = apply { this.fieldTextPlaceholderRes = text }
        fun setFieldTextPlaceholder(text: String?) = apply { this.fieldTextPlaceholder = text }

        fun setSwitchIconResList(list: List<Int>?) = apply { this.switchIconResList = list }

        fun setFieldTrailingText(text: String?) = apply { this.fieldTrailingText = text }

        fun build(): ApproveParams {
            return ApproveParams(
                titleRes = titleRes,
                title = title,
                descriptionRes = descriptionRes,
                description = description,
                approveButtonTextRes = approveButtonTextRes,
                approveButtonText = approveButtonText,
                declineButtonTextRes = declineButtonTextRes,
                declineButtonText = declineButtonText,
                fieldTextPlaceholderRes = fieldTextPlaceholderRes,
                fieldTextPlaceholder = fieldTextPlaceholder,
                switchIconResList = switchIconResList,
                fieldTrailingText = fieldTrailingText
            )
        }

    }
}
