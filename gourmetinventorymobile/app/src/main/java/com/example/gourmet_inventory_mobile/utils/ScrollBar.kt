package com.example.gourmet_inventory_mobile.utils

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DrawScrollableView(content: @Composable () -> Unit, modifier: androidx.compose.ui.Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val scrollView = ScrollView(context).apply {
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                isVerticalScrollBarEnabled = true
                isScrollbarFadingEnabled = false
            }
            val linearLayout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            }
            scrollView.addView(ComposeView(context).apply {
                setContent {
                    content()
                }
            })
            linearLayout.addView(scrollView)
            linearLayout
        }
    )
}